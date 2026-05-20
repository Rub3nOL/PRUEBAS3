const express = require('express');
const mysql = require('mysql2');

const app = express();

const conn = mysql.createConnection({
    host: '192.168.56.10',
    user: 'inventario',
    password: 'LanciaYpsilonRally2HFIntegrale',
    database: 'gestion_taller'
});

app.get('/', (req, res) => {
    conn.query('SELECT * FROM usuarios', (err, results) => {
        if (err) return res.status(500).send(err);
        res.json(results);
    });
});

app.listen(3000, () => console.log('Node corriendo en puerto 3000'));