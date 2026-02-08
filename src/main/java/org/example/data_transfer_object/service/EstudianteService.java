package org.example.data_transfer_object.service;

import org.example.data_transfer_object.dto.EstudianteCreateDTO;
import org.example.data_transfer_object.dto.EstudianteDTO;
import org.example.data_transfer_object.entity.Estudiante;
import org.example.data_transfer_object.entity.Mascota;
import org.example.data_transfer_object.mapper.EstudianteMapper;
import org.example.data_transfer_object.repository.EstudianteRepository;
import org.example.data_transfer_object.repository.MascotaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
    private final MascotaRepository mascotaRepository;

    public EstudianteService(EstudianteRepository estudianteRepository, EstudianteMapper estudianteMapper, MascotaRepository mascotaRepository) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteMapper = estudianteMapper;
        this.mascotaRepository = mascotaRepository;
    }

    // Metodo GET - Listar todos
    public List<EstudianteDTO> findAll() {
        return estudianteRepository.findAll()
                .stream()
                .map(estudianteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Metodo GET - Buscar por ID
    public Optional<EstudianteDTO> findById(Long id) {
        return estudianteRepository.findById(id)
                .map(estudianteMapper::toDTO);
    }

    // Metodo POST - Crear estudiante
    @Transactional
    public EstudianteDTO create(EstudianteCreateDTO createDTO) {
        // Convertir DTO a entidad
        Estudiante estudiante = estudianteMapper.toEntity(createDTO);

        // Primero guardar la mascota (si existe)
        if (estudiante.getMascota() != null) {
            Mascota mascotaGuardada = mascotaRepository.save(estudiante.getMascota());
            estudiante.setMascota(mascotaGuardada);
        }

        // Guardar el estudiante
        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);

        // Convertir a DTO y devolver
        return estudianteMapper.toDTO(estudianteGuardado);
    }

    // Metodo PUT - Actualizar estudiante
    @Transactional
    public EstudianteDTO update(Long id, EstudianteCreateDTO updateDTO) {
        // Buscar estudiante existente
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));

        // Actualizar datos
        estudianteMapper.updateEntity(updateDTO, estudiante);

        // Guardar mascota si fue modificada
        if (estudiante.getMascota() != null) {
            mascotaRepository.save(estudiante.getMascota());
        }

        // Guardar estudiante
        Estudiante estudianteActualizado = estudianteRepository.save(estudiante);

        // Devolver DTO
        return estudianteMapper.toDTO(estudianteActualizado);
    }
}