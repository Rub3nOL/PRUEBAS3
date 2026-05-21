// ═══════════════════════════════════════════════════════
// server.js — Servidor Node.js
//
// Hace dos cosas:
//   1. Sirve los archivos estáticos (index.html, css, js)
//   2. Expone la API /api?codigo=N que consulta MySQL
//
// Arrancar con:  node server.js
// ═══════════════════════════════════════════════════════

'use strict';

const http   = require('http');
const fs     = require('fs');
const path   = require('path');
const url    = require('url');
const bcrypt = require('bcrypt');
const mysql  = require('mysql2');   // npm install mysql2

require('dotenv').config();

// ── Configuración ────────────────────────────────────────
const CONFIG = {
  PORT:    process.env.PORT    || 3000,
  DB_HOST: process.env.DB_HOST || 'localhost',
  DB_PORT: process.env.DB_PORT || 3306,
  DB_USER: process.env.DB_USER,
  DB_PASS: process.env.DB_PASS,
  DB_NAME: process.env.DB_NAME,
};

// ── Tipos MIME para archivos estáticos ───────────────────
const MIME_TYPES = {
  '.html': 'text/html; charset=utf-8',
  '.css':  'text/css; charset=utf-8',
  '.js':   'application/javascript; charset=utf-8',
  '.ico':  'image/x-icon',
  '.png':  'image/png',
  '.jpg':  'image/jpeg',
  '.jpeg': 'image/jpeg',
  '.webp': 'image/webp',
  '.svg':  'image/svg+xml',
};

// ── Pool de conexiones MySQL ──────────────────────────────
// Un pool reutiliza conexiones en vez de abrir una nueva
// en cada petición, lo que es mucho más eficiente.
const pool = mysql.createPool({
  host:               CONFIG.DB_HOST,
  port:               CONFIG.DB_PORT,
  user:               CONFIG.DB_USER,
  password:           CONFIG.DB_PASS,
  database:           CONFIG.DB_NAME,
  waitForConnections: true,
  connectionLimit:    10,
});

// Convertir pool.query a promesas para poder usar async/await
const db = pool.promise();

// ── Función: página 404 personalizada ────────────────────
function serve404(res) {
  fs.readFile(path.join(__dirname, '404.html'), (err, data) => {
    res.writeHead(404, { 'Content-Type': 'text/html; charset=utf-8' });
    res.end(err ? '404 Not Found' : data);
  });
}

// ── Función: servir archivo estático ─────────────────────
function serveFile(res, filePath) {
  const ext     = path.extname(filePath);
  const mimeType = MIME_TYPES[ext] || 'application/octet-stream';

  fs.readFile(filePath, (err, data) => {
    if (err) {
      return serve404(res);
    }
    res.writeHead(200, { 'Content-Type': mimeType });
    res.end(data);
  });
}

// ── Función: responder con JSON ───────────────────────────
function sendJSON(res, statusCode, data) {
  res.writeHead(statusCode, {
    'Content-Type':                'application/json; charset=utf-8',
    'Access-Control-Allow-Origin': '*',   // Permite peticiones desde el navegador
  });
  res.end(JSON.stringify(data));
}

// ── Función: consulta a MySQL ─────────────────────────────
async function getMaterial(codigo) {
  const sql = `
    SELECT
      m.nombre,
      m.descripcion,
      m.cantidad,
      m.observaciones,
      e.nombre        AS estado,
      c.nombre        AS categoria,
      s.nombre        AS subcategoria
    FROM material m
    JOIN ubicaciones   u ON m.id_ubicacion    = u.codigo_armario
    JOIN estado        e ON m.id_estado       = e.id_estado
    JOIN subcategorias s ON m.id_subcategoria = s.id_subcategoria
    JOIN categorias    c ON s.id_categoria    = c.id_categoria
    WHERE u.codigo_armario = ?
    ORDER BY c.nombre, s.nombre, m.nombre
  `;
  // El ? es un parámetro seguro — evita inyección SQL
  const [rows] = await db.query(sql, [codigo]);
  return rows;
}

// ── Servidor HTTP principal ───────────────────────────────
  const server = http.createServer(async (req, res) => {
  const parsed   = url.parse(req.url, true);
  const pathname = parsed.pathname;

  // ── Ruta de la API: GET /api?codigo=N ──────────────────
  if (pathname === '/api' && req.method === 'GET') {
    const codigo = parsed.query.codigo;

    // Validar que el parámetro sea un número
    if (!codigo || !/^\d+$/.test(codigo)) {
      sendJSON(res, 400, { error: 'Parámetro "codigo" inválido o ausente' });
      return;
    }

    try {
      const filas = await getMaterial(parseInt(codigo, 10));
      sendJSON(res, 200, filas);
    } catch (err) {
      console.error('[MySQL error]', err.message);
      sendJSON(res, 500, { error: 'Error al consultar la base de datos' });
    }
    return;
  }

  // ── Login: GET /api/login → redirige al inicio ────────
  if (pathname === '/api/login' && req.method === 'GET') {
    res.writeHead(302, { Location: '/' });
    res.end();
    return;
  }

  // ── Login: POST /api/login ─────────────────────────────
  if (pathname === '/api/login' && req.method === 'POST') {
    let body = '';
    req.on('data', chunk => { body += chunk.toString().slice(0, 4096); });
    req.on('end', async () => {
      try {
        const params     = new URLSearchParams(body);
        const usuario    = (params.get('usuario')    || '').trim().slice(0, 100);
        const contrasena = (params.get('contrasena') || '').slice(0, 200);

        if (!usuario || !contrasena) {
          res.writeHead(302, { Location: '/?error=1' });
          res.end();
          return;
        }

        const [rows] = await db.query(
          'SELECT contrasena FROM usuarios WHERE nombre = ? LIMIT 1',
          [usuario]
        );

        const ok = rows.length && await bcrypt.compare(contrasena, rows[0].contrasena);
        res.writeHead(302, { Location: ok ? '/almacen.html' : '/?error=1' });
        res.end();
      } catch (err) {
        console.error('[Login error]', err.message);
        res.writeHead(302, { Location: '/?error=1' });
        res.end();
      }
    });
    return;
  }

  // ── Archivos estáticos ─────────────────────────────────
  // Normalizamos y verificamos que la ruta no salga de __dirname
  const rawPath  = pathname === '/' ? '/index.html' : pathname;
  const filePath = path.normalize(path.join(__dirname, rawPath));

  if (!filePath.startsWith(__dirname + path.sep) && filePath !== __dirname) {
    res.writeHead(403, { 'Content-Type': 'text/plain' });
    res.end('Acceso denegado');
    return;
  }

  const ext = path.extname(filePath);

  if (!MIME_TYPES[ext]) {
    return serve404(res);
  }

  serveFile(res, filePath);
});

// ── Arranque ──────────────────────────────────────────────
server.listen(CONFIG.PORT, () => {
  console.log(`Servidor arrancado en http://localhost:${CONFIG.PORT}`);
  console.log(`MySQL → ${CONFIG.DB_HOST}:${CONFIG.DB_PORT} / ${CONFIG.DB_NAME}`);
  console.log('Ctrl+C para detener');
});

// ── Manejo de errores no capturados ──────────────────────
process.on('uncaughtException', (err) => {
  console.error('[Error no capturado]', err.message);
});
