# Cambio Moneda API

API desarrollada en **Java 17** y **Spring Boot** para gestionar monedas, tasas de cambio por fecha, información de países asociada a sus monedas y control de usuarios.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 4.0.6**
- **Spring Data JPA** (Hibernate) para el ORM y persistencia.
- **PostgreSQL** como base de datos relacional.
- **Maven** para la gestión de dependencias y construcción del proyecto.

## Estructura del Proyecto

El proyecto sigue una arquitectura organizada para separar claramente la lógica de negocio y el acceso a datos, inspirada en principios de diseño de dominio:

```
src/main/java/com/example/demo/
├── DemoApplication.java                    # Clase principal para iniciar la aplicación
├── aplicacion/
│   └── servicios/                          # Lógica de negocio
│       ├── CambioMonedaServicio.java
│       ├── MonedaServicio.java
│       ├── PaisServicio.java
│       └── UsuarioServicio.java
├── dominio/
│   └── entidades/                          # Modelos de dominio (Entities mapeadas a la DB)
│       ├── CambioMoneda.java
│       ├── Moneda.java
│       ├── Pais.java
│       └── Usuario.java
├── infraestructura/
│   └── repositorios/                       # Interfaces JpaRepository para persistencia
│       ├── ICambioMonedaRepositorio.java
│       ├── IMonedaRepositorio.java
│       ├── IPaisRepositorio.java
│       └── IUsuarioRepositorio.java
└── presentacion/
    └── controladores/                      # REST Controllers (endpoints)
        ├── CambioMonedaControlador.java
        ├── MonedaControlador.java
        ├── PaisControlador.java
        └── UsuarioControlador.java
```

## Configuración de Base de Datos

La API requiere una base de datos PostgreSQL. A continuación se encuentra el script DDL (Data Definition Language) inicial que se debe ejecutar en tu servidor PostgreSQL antes de iniciar la aplicación:

```sql
-- Ejecutar primero (PostgreSQL almacena nombres en minúsculas si no se usan comillas)
CREATE DATABASE monedas; 

-- Cambiar conexión a la base de datos "monedas" antes de ejecutar lo siguiente:

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
> spring.datasource.url=jdbc:postgresql://localhost:5432/monedas
> spring.datasource.username=tu_usuario
> spring.datasource.password=tu_contraseña
> ```

## Cómo Ejecutar el Proyecto

1. Asegúrate de tener **JDK 17+** (por ejemplo, [Eclipse Temurin](https://adoptium.net/)) y **PostgreSQL** instalados.
2. Crea la base de datos y sus tablas usando el script SQL proporcionado.
3. Actualiza las credenciales en `application.properties`.
4. Abre una terminal en la carpeta `demo/` del proyecto y ejecuta:
   
   **En Windows:**
   ```bash
   .\mvnw spring-boot:run
   ```

   **En Linux/Mac:**
   ```bash
   ./mvnw spring-boot:run
   ```

5. La API se iniciará por defecto en `http://localhost:8080`.

## Endpoints de la API

Todos los endpoints retornan y reciben JSON (`Content-Type: application/json`).

### Moneda — `/api/monedas`

| Método   | URL                    | Descripción             | Body |
|----------|------------------------|-------------------------|------|
| `GET`    | `/api/monedas/`        | Listar todas            | No   |
| `GET`    | `/api/monedas/{id}`    | Obtener por ID          | No   |
| `POST`   | `/api/monedas/`        | Crear                   | JSON |
| `PUT`    | `/api/monedas/`        | Modificar               | JSON |
| `DELETE` | `/api/monedas/{id}`    | Eliminar                | No   |

**Ejemplo POST/PUT body:**
```json
{
    "moneda": "Dólar Estadounidense",
    "sigla": "USD",
    "simbolo": "$",
    "emisor": "Reserva Federal"
}
```

### Usuario — `/api/usuarios`

| Método   | URL                     | Descripción             | Body |
|----------|-------------------------|-------------------------|------|
| `GET`    | `/api/usuarios/`        | Listar todos            | No   |
| `GET`    | `/api/usuarios/{id}`    | Obtener por ID          | No   |
| `POST`   | `/api/usuarios/`        | Crear                   | JSON |
| `PUT`    | `/api/usuarios/`        | Modificar               | JSON |
| `DELETE` | `/api/usuarios/{id}`    | Eliminar                | No   |

**Ejemplo POST/PUT body:**
```json
{
    "usuario": "jperez",
    "nombre": "Juan Pérez",
    "clave": "miPassword123",
    "activo": true,
    "roles": "ADMIN"
}
```

### País — `/api/paises`

| Método   | URL                   | Descripción             | Body |
|----------|-----------------------|-------------------------|------|
| `GET`    | `/api/paises/`        | Listar todos            | No   |
| `GET`    | `/api/paises/{id}`    | Obtener por ID          | No   |
| `POST`   | `/api/paises/`        | Crear                   | JSON |
| `PUT`    | `/api/paises/`        | Modificar               | JSON |
| `DELETE` | `/api/paises/{id}`    | Eliminar                | No   |

**Ejemplo POST/PUT body** (requiere una Moneda existente):
```json
{
    "pais": "Estados Unidos",
    "codigoAlfa2": "US",
    "codigoAlfa3": "USA",
    "moneda": { "id": 1 }
}
```

### Cambio Moneda — `/api/cambiomonedas`

| Método   | URL                          | Descripción             | Body |
|----------|------------------------------|-------------------------|------|
| `GET`    | `/api/cambiomonedas/`        | Listar todos            | No   |
| `GET`    | `/api/cambiomonedas/{id}`    | Obtener por ID          | No   |
| `POST`   | `/api/cambiomonedas/`        | Crear                   | JSON |
| `PUT`    | `/api/cambiomonedas/`        | Modificar               | JSON |
| `DELETE` | `/api/cambiomonedas/{id}`    | Eliminar                | No   |

**Ejemplo POST/PUT body** (requiere una Moneda existente):
```json
{
    "moneda": { "id": 1 },
    "fecha": "2026-05-28",
    "cambio": 4250.50
}
```

> **Nota:** La fecha se envía en formato ISO 8601 (`YYYY-MM-DD`). Existe una restricción `UNIQUE` sobre la combinación `(IdMoneda, Fecha)`.
