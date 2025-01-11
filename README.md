# ForoHub

ForoHub es una API REST desarrollada con Spring Boot para gestionar un foro. Permite registrar, listar, actualizar, eliminar y buscar tópicos, proporcionando seguridad mediante autenticación JWT.

## Características

- CRUD completo para tópicos:
  - Crear, listar, actualizar y eliminar tópicos.
  - Listado de tópicos con paginación.
- Seguridad implementada con **Spring Security** y **JWT**.
- Manejo de usuarios autenticados para proteger los endpoints.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.4.1**
- **Spring Security**
- **JWT (Json Web Tokens)**
- **Hibernate/JPA**
- **MySQL**
- **Lombok**
- **Maven**

## Instalación y configuración

Sigue estos pasos para ejecutar el proyecto localmente:

### Prerrequisitos

- Java 17 o superior instalado.
- Maven instalado.
- MySQL configurado.
- IDE como IntelliJ IDEA o Eclipse.

### Configuración de base de datos

1. Crea una base de datos en MySQL con el nombre `foro_hub`.
2. Configura las credenciales en el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
