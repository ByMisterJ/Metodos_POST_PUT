package org.example.data_transfer_object.controller;

import jakarta.validation.Valid;
import org.example.data_transfer_object.dto.EstudianteCreateDTO;
import org.example.data_transfer_object.dto.EstudianteDTO;
import org.example.data_transfer_object.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        return ResponseEntity.ok(estudianteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id) {
        return estudianteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint POST - Crear estudiante
    @PostMapping
    public ResponseEntity<EstudianteDTO> createEstudiante(@Valid @RequestBody EstudianteCreateDTO createDTO) {
        EstudianteDTO nuevoEstudiante = estudianteService.create(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
    }

    // Endpoint PUT - Actualizar estudiante
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> updateEstudiante(
            @PathVariable Long id,
            @Valid @RequestBody EstudianteCreateDTO updateDTO) {
        EstudianteDTO estudianteActualizado = estudianteService.update(id, updateDTO);
        return ResponseEntity.ok(estudianteActualizado);
    }
}