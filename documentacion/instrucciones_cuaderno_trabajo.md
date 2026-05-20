# 📓 Cómo usar el Cuaderno de Trabajo en GitHub   
El cuaderno de trabajo se gestiona mediante Issues de GitHub.    
Cada jornada de trabajo se registra creando un Issue nuevo usando la plantilla "Cuaderno de trabajo".

🛠️ Configuración inicial del repositorio (una sola vez)    
El fichero de plantilla ya está incluido en el repositorio en la ruta:

.github/ISSUE_TEMPLATE/cuaderno_trabajo.md    
- No es necesario hacer nada más.    
- La plantilla aparecerá automáticamente al crear un nuevo Issue.

📝 Cómo rellenar el cuaderno cada día   
Al final de cada jornada de trabajo, el secretario del equipo (o quien tenga el rol ese día) debe:

- Ir al repositorio del equipo en GitHub   
- Hacer clic en la pestaña Issues    
- Pulsar el botón verde New issue    
- Seleccionar la plantilla "Cuaderno de trabajo"    
- Cambiar la fecha en el título: 📓 Sesión – 28/04/2026    
- Rellenar cada sección con la información del día    
- En Assignees (columna derecha), asignar el issue a todos los miembros del equipo    
- Pulsar Submit new issue    
⚠️ Importante: el issue debe crearse el mismo día de la jornada, no al día siguiente.    

🔍 Cómo consultar el cuaderno
Para ver todas las entradas del cuaderno de trabajo del equipo:

- Ir a la pestaña Issues del repositorio
- En el buscador, filtrar por etiqueta: label:cuaderno-trabajo
- Aparecerán todas las sesiones ordenadas cronológicamente
📌 Ejemplo de entrada bien rellenada
Título: 📓 Sesión – 28/04/2026

## 👥 Asistencia a la jornada de trabajo
- Ana García ✅
- Carlos López ✅
- María Fernández ✅
- David Ruiz ❌ (justificado: cita médica)

## ⚠️ Problemas e incidencias
- No conseguimos conectar la app Java con la instancia EC2-1.
  El puerto 3306 no era accesible desde fuera.

## ✅ Solución de problemas e incidencias
- Revisamos el Security Group de EC2-1 y faltaba la regla de entrada
  para el puerto 3306. La añadimos limitando el origen a la IP del laboratorio.

## 🔨 Lo que hicimos hoy
- Ana y Carlos: lanzamos las dos instancias EC2 en AWS Academy y
  configuramos Ubuntu Server en EC2-1.
- María: instaló y configuró MySQL en EC2-1 y cargó el script SQL.
- David: (ausente) tenía asignada la documentación de EC2-1 en la guía.

## 📋 Lo que vamos a hacer en la próxima sesión
- David: completar la documentación de EC2-1 en la guía de despliegue.
- Ana y Carlos: instalar Apache en EC2-2 y configurar el Security Group.
- María: verificar la conexión desde la app Java al servidor de BD.

## ⏳ Tareas retrasadas y finalizadas
- ✅ FINALIZADA: lanzamiento y configuración base de EC2-1.
- ⏳ RETRASADA: documentación de EC2-1 en guía (pasa a mañana, David).

## 💬 Aspectos que mejorar y otras observaciones
- Tenemos que revisar mejor la documentación de AWS antes de empezar para no perder tiempo con la configuración de Security Groups.
- El trabajo en equipo ha funcionado bien hoy.   
- 📂 Dónde colocar el fichero en el repositorio     
- La estructura del repositorio debe incluir la carpeta .github:   

mi-repositorio/   
├── .github/   
│   └── ISSUE_TEMPLATE/   
│       └── cuaderno_trabajo.md   ← este fichero   
├── src/   
├── docs/   
├── README.md    
└── LICENSE    
💡 La carpeta .github puede que no sea visible en el explorador de archivos del sistema porque empieza por punto. En GitHub sí aparece con normalidad.
