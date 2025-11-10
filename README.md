# Tp-Spring-Boot-Fundamentos-(To‑Do List)
Sistema de gestion de Tareas usando Spring Boot 
# Sistema de Gestión de Tareas 

Proyecto desarrollado como Trabajo Práctico utilizando **Spring Boot v3.5.7**, **Java 21**, y **Gradle** en **Windows** con **IntelliJ IDEA**.

Este sistema permite gestionar tareas en memoria utilizando buenas prácticas de arquitectura en capas, configuración externa mediante *properties*, perfiles de entorno (*profiles*), e inyección de dependencias.

---

## 📝 Descripción del Proyecto

Aplicación basada en Spring Boot que administra tareas (To‑Do) Centrado en Comprender Fundamentos de Spring Boot con:

* Carga inicial de tareas en memoria
* Agregar nuevas tareas
* Listar todas las tareas
* Filtrar tareas pendientes o completadas
* Marcar tareas como completadas
* Mostrar estadísticas dinámicas (según configuración)
* Comportamientos distintos según el entorno (dev / prod)

La ejecución principal se realiza mediante **CommandLineRunner**, que permite correr la lógica luego de que el contexto Spring se inicializa completamente.

---

##  Tecnologías Utilizadas

* **Java 21**
* **Spring Boot 3.x**
* **Gradle** como gestor de dependencias
* **Lombok** para simplificar código de modelos
* **Spring DevTools**
* **IntelliJ IDEA** (ambiente de desarrollo)
* **Windows 10/11** (plataforma)

---

##  Estructura del Proyecto

```
com.utn.tareas
├── model
│   ├── Tarea.java
│   └── Prioridad.java
├── repository
│   └── TareaRepository.java
├── service
│   ├── TareaService.java
│   ├── MensajeService.java
│   ├── MensajeDevService.java
│   └── MensajeProdService.java
└── TpSpringBootTareasApplication.java
```

---

##  Cómo ejecutar el proyecto

### 1️⃣ Clonar el repositorio

```
git clone https://github.com/usuario/repo-tareas.git
cd repo-tareas
```

### 2️⃣ Ejecutar con Gradle

```
./gradlew bootRun        # Linux / Mac
gradlew.bat bootRun      # Windows
```

También podés ejecutarlo desde IntelliJ con **Run → TareasApplication**.

---

## 🔧 Configuración de Profiles

Spring Boot permite cambiar el comportamiento del sistema según el entorno.

### **Profile de Desarrollo (dev)**

Activado por defecto en aplication.properties:

```
spring.profiles.active=dev
```

Archivo: **application-dev.properties** incluye : 

```
app.max-tareas=10
app.mostrar-estadisticas=true
logging.level.com.utn.tareas=DEBUG
```

### **Profile de Producción (prod)**

Para activar en aplication.properties:

```
spring.profiles.active=prod
```

Archivo: **application-prod.properties**

```
app.max-tareas=1000
app.mostrar-estadisticas=false
logging.level.com.utn.tareas=ERROR
```

###  Cómo cambiar entre profiles

Abrir `src/main/resources/application.properties` y editar:

```
spring.profiles.active=dev   # o prod
```

---

## Flujo de ejecución del sistema

Durante el arranque, el **CommandLineRunner** ejecuta:

1. Mostrar mensaje de bienvenida (según profile)
2. Mostrar configuración actual
3. Listar tareas iniciales
4. Agregar alugunas  tareas nuevas (5)
5. Listar tareas pendientes
6. Marcar una tarea como completada
7. Mostrar estadísticas
8. Listar tareas completadas
9. Mostrar mensaje final

---

##  Capturas de pantalla en Carpeta /Sceenshots : 

Incluye:

* Ejecución con profile **dev**
* Ejecución con profile **prod**
* Comportamientos diferenciados (límite de tareas, estadísticas, mensajes)

---

## 💭 Conclusiones Personales

* Comprension de cómo se estructura una aplicación Spring Boot real.
* Aprendimos a realizar inyección de dependencias por constructor.
* Uso *properties* para configurar parámetros externos.
* Aprendí a utilizar **profiles** para cambiar el comportamiento de la configuracion de la aplicacion.
* Practiqué el uso de **CommandLineRunner** para lógica de inicio y pruebas durante el Desarrollo.

---

##  Autor

**Nombre:** Alejandro Claudio Churquina
**Legajo:** 50848
**Curso:** 3k10 ,Sistemas - UTN -FRM 

---

