<!-- ================================================== -->
<!-- IMAGEN DE PORTADA                                  -->
<!-- Añadir aquí la imagen/banner principal del proyecto -->
<!-- Ejemplo: ![Portada](ruta/a/la/imagen.png)           -->
<!-- ================================================== -->

<h1 align="center">⚙️ RETA 2526 – EQUIPO 1</h1>

<h3 align="center">Documentación, diseño de base de datos y aplicación Java</h3>

<p align="center">
  <img src="https://img.shields.io/badge/Estado-Finalizado-brightgreen?style=for-the-badge&logo=checkmarx&logoColor=white" alt="Estado">
  <img src="https://img.shields.io/badge/Última%20actualización-Mayo%202026-blue?style=for-the-badge&logo=github&logoColor=white" alt="Última actualización">
  <img src="https://img.shields.io/badge/Contribuidores-4-orange?style=for-the-badge&logo=github&logoColor=white" alt="Contribuidores">
  <img src="https://img.shields.io/badge/Licencia-All%20Rights%20Reserved-red?style=for-the-badge&logo=opensourceinitiative&logoColor=white" alt="Licencia">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/Apache-D22128?style=for-the-badge&logo=apache&logoColor=white" alt="Apache">
  <img src="https://img.shields.io/badge/Ubuntu%20Server-E95420?style=for-the-badge&logo=ubuntu&logoColor=white" alt="Ubuntu">
  <img src="https://img.shields.io/badge/SSH-4D4D4D?style=for-the-badge&logo=openssh&logoColor=white" alt="SSH">
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white" alt="Git">
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub">
  <img src="https://img.shields.io/badge/Draw.io-F08705?style=for-the-badge&logo=diagramsdotnet&logoColor=white" alt="Draw.io">
  <img src="https://img.shields.io/badge/Markdown-000000?style=for-the-badge&logo=markdown&logoColor=white" alt="Markdown">
</p>

---

# 📚 Tabla de contenidos

* [📖 Descripción del proyecto](#-descripción-del-proyecto)
* [👥 Equipo](#-equipo)
* [📁 Estructura del repositorio](#-estructura-del-repositorio)
* [🛠️ Tecnologías y herramientas](#️-tecnologías-y-herramientas)
* [📝 Documentación](#-documentación)
* [📊 Diagramas](#-diagramas)
* [🗺️ Roadmap](#️-roadmap)
* [🤝 Contribuciones](#-contribuciones)
* [📄 Licencia](#-licencia)

---

# 📖 Descripción del proyecto

Proyecto colaborativo desarrollado por el **Equipo 1** para la materia **RETA 2526**.

El objetivo principal del repositorio es centralizar toda la documentación técnica, diagramas, diseño de base de datos y el desarrollo de una aplicación Java conectada a una base de datos MySQL.

La arquitectura del proyecto contempla:

* Una **aplicación Java** como núcleo principal del sistema.
* Una **base de datos MySQL** alojada en un servidor Ubuntu.
* Un servidor **Apache** encargado de servir la aplicación.
* Acceso y administración remota del servidor mediante **SSH**.

---

# 👥 Equipo

| Integrante    | GitHub                                                             |
| ------------- | ------------------------------------------------------------------ |
| Daniel Zabala | [@foover](https://github.com/foover)                               |
| Joaquín López | [@joaquinlopezperez](https://github.com/joaquinlopezperez)         |
| Rubén Ortiz   | [@Rub3nOL](https://github.com/Rub3nOL)                             |
| Mario Revilla | [@MarioRevillaAbarquero](https://github.com/MarioRevillaAbarquero) |

---

# 📁 Estructura del repositorio

```text
.
├── BD/
│   └── Diagramas/
│       ├── DiagramaER.jpg
│       ├── ModeloRelacional.jpg
│       ├── DiagramaClases.jpg
│       └── DiagramaCasosDeUso.jpg
├── documentacion/
│   ├── cuaderno_trabajo.md
│   ├── instrucciones_cuaderno_trabajo.md
│   ├── diagrama_clases.md
│   ├── diagrama_casos_uso.md
│   └── notas.txt
├── virtualizacion/
│   └── manual_usuario.md
├── .github/
│   └── ISSUE_TEMPLATE/
├── LICENSE
└── README.md
```

> ℹ️ Visita las distintas ramas del repositorio para ver el desarrollo completo del código.

---

# 🛠️ Tecnologías y herramientas

| Tecnología / Herramienta | Uso dentro del proyecto                        |
| ------------------------ | ---------------------------------------------- |
| Java + Maven             | Desarrollo de la aplicación principal          |
| MySQL                    | Sistema de gestión de base de datos            |
| Apache                   | Servidor web y despliegue de la aplicación     |
| Ubuntu Server            | Sistema operativo del servidor                 |
| SSH                      | Acceso y administración remota del servidor    |
| Draw.io / diagrams.net   | Diseño de diagramas y modelado UML             |
| Git / GitHub             | Control de versiones y colaboración            |
| Markdown                 | Documentación técnica                          |

---

# 📝 Documentación

* [Cuaderno de trabajo](./documentacion/cuaderno_trabajo.md)
* [Documentación – Diagrama de Clases](./documentacion/diagrama_clases.md)
* [Documentación – Diagrama de Casos de Uso](./documentacion/diagrama_casos_uso.md)
* [Manual de usuario](./virtualizacion/manual_usuario.md)

---

# 📊 Diagramas

| Diagrama            | Estado        | Archivo                                    |
| ------------------- | ------------- | ------------------------------------------ |
| Diagrama ER         | 🟢 Completado | `BD/Diagramas/DiagramaER.jpg`              |
| Modelo relacional   | 🟢 Completado | `BD/Diagramas/ModeloRelacional.jpg`        |
| Diagrama de clases  | 🟢 Completado | `BD/Diagramas/DiagramaClases.jpg`          |
| Casos de uso        | 🟢 Completado | `BD/Diagramas/DiagramaCasosDeUso.jpg`      |

---

# 🗺️ Roadmap

* [x] **Fase 1: Análisis y modelado de datos**
  Definición de requerimientos, elaboración del diagrama ER, construcción del modelo relacional y documentación técnica inicial.

* [x] **Fase 2: Implementación de la base de datos**
  Scripts SQL de creación, inserción de datos de prueba y consultas de verificación.

* [x] **Fase 3: Desarrollo de la aplicación Java**
  Desarrollo con Maven conectando la aplicación a MySQL a través del servidor Apache.

* [x] **Fase 4: Despliegue en servidor Ubuntu + Apache**
  Configuración de Ubuntu Server, instalación y segurización de MySQL, configuración de Apache y acceso SSH. Despliegue y verificación de conexión entre servicios.

* [x] **Fase 5: Pruebas finales y ajustes**
  Pruebas de integración y carga, corrección de errores, documentación de despliegue y manual de usuario.

---

# 🤝 Contribuciones

Este repositorio corresponde a un **trabajo académico** desarrollado íntegramente por el **Equipo 1** para la materia RETA 2526.

Toda la organización del trabajo, asignación de tareas y seguimiento del progreso puede consultarse en el cuaderno de trabajo y la documentación asociada del repositorio.

> ⚠️ **Este repositorio no acepta contribuciones externas.** Cualquier uso, reproducción o distribución del contenido sin autorización expresa de los autores está estrictamente prohibido. Consulta la sección de [Licencia](#-licencia).

---

# 📄 Licencia

**© 2026 Equipo 1 – Daniel Zabala, Joaquín López, Rubén Ortiz, Mario Revilla. Todos los derechos reservados.**

Este repositorio y todo su contenido —incluyendo código fuente, documentación, diagramas, scripts SQL y cualquier otro material aquí publicado— es propiedad exclusiva de sus autores.

**Queda expresamente prohibido:**

- Copiar, reproducir o redistribuir total o parcialmente cualquier parte de este proyecto.
- Modificar, adaptar o crear trabajos derivados basados en este contenido.
- Utilizar este proyecto o cualquiera de sus partes con fines académicos, comerciales o personales sin autorización escrita de los autores.
- Presentar este trabajo, o cualquier fragmento del mismo, como propio en cualquier contexto académico o profesional.

El acceso público a este repositorio tiene como único propósito la **visualización** del trabajo realizado por el equipo. No constituye una cesión de derechos de ningún tipo.

Para cualquier consulta sobre el uso de este contenido, contacta con los autores a través de sus perfiles de GitHub.

> 🚫 *Ninguna parte de este proyecto puede ser copiada ni utilizada sin permiso expreso y por escrito de los autores.*
