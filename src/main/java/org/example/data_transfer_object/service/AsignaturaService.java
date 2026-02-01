package org.example.data_transfer_object.service;

import org.example.data_transfer_object.dto.AsignaturaDTO;
import org.example.data_transfer_object.mapper.AsignaturaMapper;
import org.example.data_transfer_object.repository.AsignaturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;
    private final AsignaturaMapper asignaturaMapper;

    public AsignaturaService(AsignaturaRepository asignaturaRepository, AsignaturaMapper asignaturaMapper) {
        this.asignaturaRepository = asignaturaRepository;
        this.asignaturaMapper = asignaturaMapper;
    }

    public List<AsignaturaDTO> findAll() {
        return asignaturaRepository.findAll()
            .stream()
            .map(asignaturaMapper::toDTO)
            .collect(Collectors.toList());
    }

    public Optional<AsignaturaDTO> findById(Long id) {
        return asignaturaRepository.findById(id)
            .map(asignaturaMapper::toDTO);
    }
}
