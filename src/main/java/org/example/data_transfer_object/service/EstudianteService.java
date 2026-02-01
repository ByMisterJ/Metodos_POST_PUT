package org.example.data_transfer_object.service;

import org.example.data_transfer_object.dto.EstudianteDTO;
import org.example.data_transfer_object.mapper.EstudianteMapper;
import org.example.data_transfer_object.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;

    public EstudianteService(EstudianteRepository estudianteRepository, EstudianteMapper estudianteMapper) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteMapper = estudianteMapper;
    }

    public List<EstudianteDTO> findAll() {
        return estudianteRepository.findAll()
            .stream()
            .map(estudianteMapper::toDTO)
            .collect(Collectors.toList());
    }

    public Optional<EstudianteDTO> findById(Long id) {
        return estudianteRepository.findById(id)
            .map(estudianteMapper::toDTO);
    }
}
