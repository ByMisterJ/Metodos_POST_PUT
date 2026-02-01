# Proyecto Hogwarts - Data Transfer Objects (DTO)

## Descripción
Este proyecto implementa el patrón Data Transfer Object (DTO) para una aplicación Spring Boot que gestiona información de Hogwarts (casas, profesores, estudiantes, asignaturas y mascotas).

## Estructura del Proyecto

### Entidades (Entity)
- **Casa**: Representa las casas de Hogwarts (Gryffindor, Slytherin, Ravenclaw, Hufflepuff)
- **Profesor**: Representa a los profesores del colegio
- **Estudiante**: Representa a los estudiantes
- **Mascota**: Representa las mascotas de los estudiantes
- **Asignatura**: Representa las asignaturas impartidas
- **EstudianteAsignatura**: Tabla de relación para gestionar las calificaciones de estudiantes en asignaturas

### DTOs (Data Transfer Objects)
Los DTOs están diseñados para exponer solo la información necesaria en las respuestas de la API:

- **CasaDTO**: Contiene información de la casa con el jefe (ProfesorDTO) y lista de nombres de estudiantes
- **ProfesorDTO**: Información básica del profesor
- **EstudianteDTO**: Información del estudiante con su casa (nombre), mascota (MascotaDTO) y asignaturas con calificaciones
- **MascotaDTO**: Información de la mascota con el nombre de su dueño
- **AsignaturaDTO**: Información de la asignatura con el nombre del profesor
- **AsignaturaCalificacionDTO**: DTO embebido con nombre de asignatura y calificación

### Mappers
Clases responsables de convertir entidades a DTOs:
- **CasaMapper**
- **ProfesorMapper**
- **EstudianteMapper**
- **MascotaMapper**
- **AsignaturaMapper**
- **AsignaturaCalificacionMapper**

### Servicios
Capa de lógica de negocio que utiliza los mappers para devolver DTOs:
- **CasaService**
- **ProfesorService**
- **EstudianteService**
- **MascotaService**
- **AsignaturaService**

### Controladores
Endpoints REST que devuelven DTOs:
- **CasaController** - `/api/casas`
- **ProfesorController** - `/api/profesores`
- **EstudianteController** - `/api/estudiantes`
- **MascotaController** - `/api/mascotas`
- **AsignaturaController** - `/api/asignaturas`

## Endpoints de la API

Todos los controladores implementan dos endpoints GET:

### Listar todos
- `GET /api/casas` - Lista todas las casas
- `GET /api/profesores` - Lista todos los profesores
- `GET /api/estudiantes` - Lista todos los estudiantes
- `GET /api/mascotas` - Lista todas las mascotas
- `GET /api/asignaturas` - Lista todas las asignaturas

### Obtener por ID
- `GET /api/casas/{id}` - Obtiene una casa específica
- `GET /api/profesores/{id}` - Obtiene un profesor específico
- `GET /api/estudiantes/{id}` - Obtiene un estudiante específico
- `GET /api/mascotas/{id}` - Obtiene una mascota específica
- `GET /api/asignaturas/{id}` - Obtiene una asignatura específica

## Ventajas del Patrón DTO

1. **Separación de responsabilidades**: Las entidades del modelo de datos están separadas de las respuestas de la API
2. **Evita exposición de estructura interna**: No se exponen las relaciones JPA directamente
3. **Previene bucles infinitos**: Al convertir relaciones bidireccionales a datos simples
4. **Flexibilidad**: Se puede personalizar la respuesta según las necesidades del cliente
5. **Seguridad**: No se exponen campos sensibles o innecesarios

## Configuración

### Base de Datos
El proyecto está configurado para usar PostgreSQL. Ajusta las credenciales en `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hogwarts
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### Datos de Prueba
El proyecto incluye un `DataInitializer` que carga datos de ejemplo al iniciar:
- 4 profesores (McGonagall, Snape, Flitwick, Sprout)
- 4 casas de Hogwarts con sus jefes
- 3 estudiantes (Harry Potter, Hermione Granger, Ron Weasley)
- 3 mascotas (Hedwig, Crookshanks, Scabbers)
- 4 asignaturas (Pociones, Transformaciones, Encantamientos, Herbología)
- Relaciones estudiante-asignatura con calificaciones

## Cómo Ejecutar

1. Asegúrate de tener PostgreSQL instalado y ejecutándose
2. Crea la base de datos:
   ```sql
   CREATE DATABASE hogwarts;
   ```
3. Ejecuta la aplicación:
   ```bash
   ./gradlew bootRun
   ```
4. Accede a los endpoints en `http://localhost:8080/api/...`

## Ejemplos de Respuestas

### GET /api/estudiantes/1
```json
{
  "id": 1,
  "nombre": "Harry Potter",
  "anyoCurso": 5,
  "fechaNacimiento": "1980-07-31",
  "casa": "Gryffindor",
  "mascota": {
    "id": 1,
    "nombre": "Hedwig",
    "especie": "Lechuza",
    "estudiante": "Harry Potter"
  },
  "asignaturas": [
    {
      "asignatura": "Pociones",
      "calificacion": 7.5
    },
    {
      "asignatura": "Transformaciones",
      "calificacion": 8.0
    },
    {
      "asignatura": "Encantamientos",
      "calificacion": 8.5
    }
  ]
}
```

### GET /api/casas/1
```json
{
  "id": 1,
  "nombre": "Gryffindor",
  "fundador": "Godric Gryffindor",
  "fantasma": "Casi Decapitado Nick",
  "jefe": {
    "id": 1,
    "nombre": "Minerva McGonagall",
    "asignatura": "Transformaciones",
    "fechaInicio": "1956-01-01"
  },
  "estudiantes": [
    "Harry Potter",
    "Hermione Granger",
    "Ron Weasley"
  ]
}
```

## Tecnologías Utilizadas
- Java 21
- Spring Boot 4.0.2
- Spring Data JPA
- PostgreSQL
- Gradle
