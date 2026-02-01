# Arquitectura del Proyecto - Patrón DTO

## Diagrama de Capas

```
┌─────────────────────────────────────────────────────────────┐
│                     CLIENTE (Browser/App)                    │
└─────────────────────────────────────────────────────────────┘
                              ↓ HTTP GET
┌─────────────────────────────────────────────────────────────┐
│                   CAPA DE CONTROLADORES                      │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │CasaController│  │ProfesorCont. │  │EstudianteCon.│  ...  │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
│         ↓ ResponseEntity<DTO>                               │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                     CAPA DE SERVICIOS                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │ CasaService  │  │ProfesorServ. │  │EstudianteSer.│  ...  │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
│         ↓ List<DTO> / Optional<DTO>                         │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                      CAPA DE MAPPERS                         │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │  CasaMapper  │  │ProfesorMappe │  │EstudianteMap │  ...  │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
│         ↓ Entity → DTO                                       │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                   CAPA DE REPOSITORIOS                       │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │CasaReposito. │  │ProfesorRepo. │  │EstudianteRep │  ...  │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
│         ↓ JpaRepository<Entity, Long>                        │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                     CAPA DE ENTIDADES                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │     Casa     │  │   Profesor   │  │  Estudiante  │  ...  │
│  └──────────────┘  └──────────────┘  └──────────────┘       │
│         ↓ JPA Entities with @Entity                          │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                  BASE DE DATOS (PostgreSQL)                  │
│     Tables: casas, profesores, estudiantes, mascotas,       │
│             asignaturas, estudiante_asignatura               │
└─────────────────────────────────────────────────────────────┘
```

## Flujo de Datos (Ejemplo: GET /api/estudiantes/1)

```
1. HTTP Request: GET /api/estudiantes/1
   ↓
2. EstudianteController.getEstudianteById(1)
   ↓
3. EstudianteService.findById(1)
   ↓
4. EstudianteRepository.findById(1)
   ↓
5. JPA/Hibernate consulta la BD
   ↓
6. Retorna Optional<Estudiante> (Entity)
   ↓
7. EstudianteMapper.toDTO(estudiante)
   - Convierte Estudiante → EstudianteDTO
   - Llama a MascotaMapper para la mascota
   - Llama a AsignaturaCalificacionMapper para asignaturas
   - Extrae nombre de la casa (String)
   ↓
8. Retorna Optional<EstudianteDTO>
   ↓
9. ResponseEntity.ok(estudianteDTO)
   ↓
10. HTTP Response: JSON del EstudianteDTO
```

## Modelo de Datos (Relaciones entre Entidades)

```
┌──────────────┐          ┌──────────────┐
│   Profesor   │◄─────┬───│     Casa     │
└──────────────┘      │   └──────────────┘
      ▲               │          │
      │               │          │ 1:N
      │ N:1           │          ▼
      │         jefe  │   ┌──────────────┐
┌──────────────┐      └───│  Estudiante  │
│  Asignatura  │          └──────────────┘
└──────────────┘                 │
      │                          │ 1:1
      │ N:M                      ▼
      │              ┌──────────────┐
      │              │   Mascota    │
      │              └──────────────┘
      ▼
┌─────────────────────┐
│EstudianteAsignatura │
│  - calificacion     │
└─────────────────────┘
```

## DTOs y sus Relaciones

```
┌──────────────────┐
│    CasaDTO       │
│  - id            │
│  - nombre        │
│  - fundador      │
│  - fantasma      │
│  - jefe ───────┐ │
│  - estudiantes  │ │
└─────────────────┘ │
                    │
       ┌────────────┘
       │
       ▼
┌──────────────────┐
│  ProfesorDTO     │
│  - id            │
│  - nombre        │
│  - asignatura    │
│  - fechaInicio   │
└──────────────────┘


┌──────────────────────────────┐
│      EstudianteDTO           │
│  - id                        │
│  - nombre                    │
│  - anyoCurso                 │
│  - fechaNacimiento           │
│  - casa (String)             │
│  - mascota ──────────┐       │
│  - asignaturas ──┐   │       │
└──────────────────│───│───────┘
                   │   │
       ┌───────────┘   └────────────┐
       │                            │
       ▼                            ▼
┌──────────────────────┐   ┌──────────────────┐
│AsignaturaCalificación│   │   MascotaDTO     │
│       DTO            │   │  - id            │
│  - asignatura        │   │  - nombre        │
│  - calificacion      │   │  - especie       │
└──────────────────────┘   │  - estudiante    │
                           └──────────────────┘


┌──────────────────┐
│  AsignaturaDTO   │
│  - id            │
│  - nombre        │
│  - aula          │
│  - obligatoria   │
│  - profesor      │
│    (String)      │
└──────────────────┘
```

## Características Clave del Patrón DTO

### 1. Prevención de Bucles Infinitos
**Problema sin DTO:**
```
Estudiante → Casa → List<Estudiante> → Casa → ...
```

**Solución con DTO:**
```
EstudianteDTO tiene "casa" como String (nombre), no objeto Casa completo
CasaDTO tiene "estudiantes" como List<String> (nombres), no objetos Estudiante
```

### 2. Ocultación de Estructura Interna
- Los DTOs solo exponen lo necesario
- Las anotaciones JPA (@Entity, @OneToMany, etc.) no se exponen
- Se pueden agregar/eliminar campos en entities sin afectar la API

### 3. Flexibilidad en Respuestas
- EstudianteDTO incluye asignaturas con calificaciones embebidas
- CasaDTO incluye ProfesorDTO completo del jefe
- MascotaDTO incluye nombre del estudiante, no objeto completo

### 4. Mapeo Unidireccional
```java
// Mapper convierte solo en una dirección: Entity → DTO
public EstudianteDTO toDTO(Estudiante estudiante) {
    // Nunca crea referencia circular
    // Casa → solo nombre (String)
    // Mascota → MascotaDTO (sin referencia back a Estudiante)
}
```

## Ventajas Técnicas

| Aspecto | Sin DTO | Con DTO |
|---------|---------|---------|
| Serialización JSON | Puede fallar con LazyInitializationException | Siempre funciona |
| Bucles infinitos | Posibles con relaciones bidireccionales | Imposibles |
| Exposición de datos | Todo el modelo de datos | Solo lo necesario |
| Cambios en BD | Afectan directamente a la API | No afectan a la API |
| Rendimiento | Puede cargar datos innecesarios | Carga solo lo requerido |
| Testing | Difícil (dependencias JPA) | Fácil (POJOs simples) |

## Ejemplo de Respuesta Real

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

### GET /api/estudiantes/2
```json
{
  "id": 2,
  "nombre": "Hermione Granger",
  "anyoCurso": 5,
  "fechaNacimiento": "1979-09-19",
  "casa": "Gryffindor",
  "mascota": {
    "id": 2,
    "nombre": "Crookshanks",
    "especie": "Gato",
    "estudiante": "Hermione Granger"
  },
  "asignaturas": [
    {
      "asignatura": "Pociones",
      "calificacion": 9.8
    },
    {
      "asignatura": "Transformaciones",
      "calificacion": 9.9
    },
    {
      "asignatura": "Encantamientos",
      "calificacion": 10.0
    },
    {
      "asignatura": "Herbología",
      "calificacion": 9.7
    }
  ]
}
```

## Conclusión

Esta arquitectura sigue las mejores prácticas de Spring Boot:
- **Separación de capas**: Cada capa tiene una responsabilidad clara
- **Inyección de dependencias**: @Component, @Service, @RestController
- **Patrón DTO**: Separación entre modelo de datos y respuestas API
- **Mappers dedicados**: Conversión centralizada y reutilizable
- **RESTful API**: Endpoints siguiendo convenciones REST
