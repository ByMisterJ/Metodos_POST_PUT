package org.example.data_transfer_object.service;

import org.example.data_transfer_object.dto.ProfesorDTO;
import org.example.data_transfer_object.mapper.ProfesorMapper;
import org.example.data_transfer_object.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final ProfesorMapper profesorMapper;

    public ProfesorService(ProfesorRepository profesorRepository, ProfesorMapper profesorMapper) {
        this.profesorRepository = profesorRepository;
        this.profesorMapper = profesorMapper;
    }

    public List<ProfesorDTO> findAll() {
        return profesorRepository.findAll()
            .stream()
            .map(profesorMapper::toDTO)
            .collect(Collectors.toList());
    }

    public Optional<ProfesorDTO> findById(Long id) {
        return profesorRepository.findById(id)
            .map(profesorMapper::toDTO);
    }
}
