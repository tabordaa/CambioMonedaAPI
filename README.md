# Cambio Moneda API

API desarrollada en **Java 17** y **Spring Boot** para gestionar monedas, tasas de cambio por fecha, información de países asociada a sus monedas y control de usuarios.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot** (v3.x / v4.x según configuración local)
- **Spring Data JPA** (Hibernate) para el ORM y persistencia.
- **PostgreSQL** como base de datos relacional.
- **Maven** para la gestión de dependencias y construcción del proyecto.

## Estructura del Proyecto

El proyecto sigue una arquitectura organizada para separar claramente la lógica de negocio y el acceso a datos, inspirada en principios de diseño de dominio:

```
src/main/java/com/example/demo/
├── DemoApplication.java                # Clase principal para iniciar la aplicación
├── dominio/
│   └── entidades/                      # Modelos de dominio (Entities mapeadas a la DB)
│       ├── CambioMoneda.java
│       ├── Moneda.java
│       ├── Pais.java
│       └── Usuario.java
└── infraestructura/
    └── repositorios/                   # Interfaces JpaRepository para persistencia
        ├── ICambioMonedaRepositorio.java
        ├── IMonedaRepositorio.java
        ├── IPaisRepositorio.java
        └── IUsuarioRepositorio.java
```

## Configuración de Base de Datos

La API requiere una base de datos PostgreSQL. A continuación se encuentra el script DDL (Data Definition Language) inicial que se debe ejecutar en tu servidor PostgreSQL antes de iniciar la aplicación:

```sql
-- Ejecutar primero
CREATE DATABASE Monedas; 

-- Cambiar conexión a la base de datos "Monedas" antes de ejecutar lo siguiente:

/* Crear tabla MONEDA */
CREATE TABLE Moneda( 
    Id SERIAL PRIMARY KEY,
    Moneda VARCHAR(100) NOT NULL,
    Sigla VARCHAR(5) NOT NULL,
    Simbolo VARCHAR(5) NULL,
    Emisor VARCHAR(100) NULL,
    Imagen BYTEA NULL
);

CREATE UNIQUE INDEX ixMoneda ON Moneda(Moneda);

/* Crear tabla CAMBIOMONEDA */
CREATE TABLE CambioMoneda( 
    Id SERIAL PRIMARY KEY,
    IdMoneda int NOT NULL,
    CONSTRAINT fkCambioMoneda_IdMoneda FOREIGN KEY (IdMoneda) REFERENCES Moneda(Id),
    Fecha DATE NOT NULL,
    Cambio FLOAT NOT NULL
);

CREATE UNIQUE INDEX ixCambioMoneda ON CambioMoneda(IdMoneda, Fecha);

/* Crear tabla PAIS */
CREATE TABLE Pais(
    Id SERIAL PRIMARY KEY,
    Pais varchar(50) not null,
    CodigoAlfa2 varchar(5) not null,
    CodigoAlfa3 varchar(5) not null, 
    IdMoneda int NOT NULL,
    CONSTRAINT fkPais_IdMoneda FOREIGN KEY (IdMoneda) REFERENCES Moneda(Id),
    Mapa BYTEA NULL,
    Bandera BYTEA NULL
);

CREATE UNIQUE INDEX ixPais ON Pais(Pais);
    
/* Crear tabla USUARIO */
CREATE TABLE Usuario( 
    Id SERIAL PRIMARY KEY,
    Usuario VARCHAR(100) NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
    Clave VARCHAR(100) NOT NULL,
    Activo BOOL DEFAULT(true) NOT NULL,
    Foto BYTEA NULL,
    Roles VARCHAR(100) NULL
);
```

> **Nota:** Recuerda actualizar el archivo `src/main/resources/application.properties` con las credenciales correctas de tu base de datos:
> ```properties
> spring.datasource.url=jdbc:postgresql://localhost:5432/Monedas
> spring.datasource.username=tu_usuario
> spring.datasource.password=tu_contraseña
> spring.jpa.hibernate.ddl-auto=validate
> spring.jpa.show-sql=true
> ```

## Cómo Ejecutar el Proyecto

1. Asegúrate de tener **Java 17+** y **PostgreSQL** instalados en tu entorno.
2. Crea la base de datos y sus tablas usando el script SQL proporcionado.
3. Clona o descarga este repositorio y abre una terminal en la ruta principal del proyecto.
4. Ejecuta el comando de Maven para iniciar la aplicación:
   
   **En Windows:**
   ```bash
   .\mvnw spring-boot:run
   ```

   **En Linux/Mac:**
   ```bash
   ./mvnw spring-boot:run
   ```

5. La API se iniciará por defecto en el puerto `8080`.
