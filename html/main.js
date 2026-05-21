/* ═══════════════════════════════════════════
   main.js — Lógica de interacción + fetch
   La única diferencia respecto a la versión PHP:
   la URL de la API es /api?codigo=N
   en vez de api.php?codigo=N
════════════════════════════════════════════ */

// Endpoint del servidor Node.js que devuelve el contenido de una ubicación
const API_URL = '/api';

// Referencias a los elementos del panel de información en el HTML
const panel        = document.getElementById('info-panel');
const elCodigo     = document.getElementById('info-codigo');         // título con el número de ubicación
const elTitle      = document.getElementById('info-title');         // nombre del hueco (ej. "Estante A1")
const elLoading    = document.getElementById('info-loading');      // spinner mientras carga
const elError      = document.getElementById('info-error');       // mensaje de error
const elTablaWrap  = document.getElementById('info-tabla-wrap'); // contenedor de la tabla
const elTbody      = document.getElementById('info-tbody');     // cuerpo de la tabla con los datos
const elVacio      = document.getElementById('info-vacio');    // mensaje "sin material registrado"
const closeBtn     = document.getElementById('info-close');   // botón ✕ para cerrar

// Celda del armario que está seleccionada actualmente (null si ninguna)
let selectedCell = null;

// Muestra el spinner y oculta el resto de estados del panel
function showLoading() {
  elLoading.style.display   = 'flex';
  elError.style.display     = 'none';
  elTablaWrap.style.display = 'none';
  elVacio.style.display     = 'none';
}

// Muestra un mensaje de error y oculta el resto de estados
function showError(msg) {
  elLoading.style.display   = 'none';
  elTablaWrap.style.display = 'none';
  elVacio.style.display     = 'none';
  elError.style.display     = 'block';
  elError.textContent       = '⚠ ' + msg;
}

// Rellena la tabla con los datos recibidos de la API
// Si el array está vacío muestra el mensaje "sin material"
function showTabla(filas) {
  elLoading.style.display = 'none';
  elError.style.display   = 'none'; 

  if (filas.length === 0) {
    elTablaWrap.style.display = 'none';
    elVacio.style.display     = 'block';
    return;
  }

  elVacio.style.display     = 'none';
  elTablaWrap.style.display = 'block';
  elTbody.innerHTML         = '';

  filas.forEach(function (item) {
    const tr = document.createElement('tr');

    // Columnas en orden: nombre, categoría, cantidad, estado (badge), observaciones
    const cols = [
      item.nombre,
      item.categoria + ' / ' + item.subcategoria,
      item.cantidad,
      null,   // estado → se renderiza como badge, no como texto plano
      item.observaciones || '—',
    ];

    cols.forEach(function (val, i) {
      const td = document.createElement('td');
      if (i === 3) {
        // La columna de estado usa un <span> con clase CSS en vez de texto
        const badge = document.createElement('span');
        badge.className   = 'badge badge-' + item.estado.replace(' ', '-');
        badge.textContent = item.estado;
        td.appendChild(badge);
      } else {
        td.textContent = val;
      }
      tr.appendChild(td);
    });

    elTbody.appendChild(tr);
  });
}

// Llama a la API con el código de ubicación y actualiza el panel según la respuesta
function fetchUbicacion(codigo) {
  showLoading();

  fetch(API_URL + '?codigo=' + encodeURIComponent(codigo))
    .then(function (res) {
      if (!res.ok) throw new Error('Error HTTP ' + res.status);
      return res.json();
    })
    .then(function (data) {
      if (data.error) {
        showError(data.error);
      } else {
        showTabla(data);
      }
    })
    .catch(function (err) {
      showError('No se pudo conectar con el servidor. ' + err.message);
    });
}

// Abre el panel lateral con el nombre de la celda y lanza la petición a la API
function openPanel(cell) {
  elCodigo.textContent = 'Ubicación #' + cell.dataset.codigo;
  elTitle.textContent  = cell.dataset.label;
  panel.classList.add('visible');
  fetchUbicacion(cell.dataset.codigo);
}

// Cierra el panel y quita el resaltado de la celda seleccionada
function closePanel() {
  panel.classList.remove('visible');
  if (selectedCell) {
    selectedCell.classList.remove('selected');
    selectedCell = null;
  }
}

// Gestiona el clic sobre una celda del armario:
// si ya estaba seleccionada la cierra; si no, abre el panel con su información
function handleCellClick(e) {
  const cell = e.currentTarget;
  if (cell === selectedCell) { closePanel(); return; }
  if (selectedCell) selectedCell.classList.remove('selected');
  selectedCell = cell;
  cell.classList.add('selected');
  openPanel(cell);
}

// Registra todos los listeners: clic en celdas, botón cerrar y tecla Escape
function init() {
  document.querySelectorAll('.celda').forEach(function (c) {
    c.addEventListener('click', handleCellClick);
  });
  closeBtn.addEventListener('click', closePanel);
  document.addEventListener('keydown', function (e) {
    if (e.key === 'Escape') closePanel();
  });
}

// Si el DOM ya está listo se inicializa directamente; si no, espera al evento
if (document.readyState === 'loading') {
  document.addEventListener('DOMContentLoaded', init);
} else {
  init();
}
