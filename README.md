### CRUD Spring Boot + React Redux ###

## Base de datos
- MySQL para la persistencia de datos.
- Script ubicado en la carpeta "script-sql", cargar en motor.

## Backend
**Aplicación Spring Boot Rest API**
- Crear
- Eliminar
- Actualizar
- Leer

#### Tecnologías:
- Spring Boot 2.1
- Java 1.8
- Desarrollado con IntelliJ IDEA


##### En la raíz del proyecto `/spring-boot-mysql-crud`, ejecutar en consola: `mvn spring-boot:run` (supone correcta instalación de Maven).

#### Swagger:
[http://localhost:8080/swagger-ui.html#/task-controller](http://localhost:8080/swagger-ui.html#/task-controller)

## Frontend
**React Redux. componentes, Redux (actions, reducers, store).**

- Agregar tarea
- Modificar tarea
- Eliminar tarea
- Lista de tareas registradas

##### En la raíz del proyecto `/react-redux`, ejecutar en consola:
  - `npm install` (para inicializar dependencias Redux, node-sass, etc)
  - `npm run start`