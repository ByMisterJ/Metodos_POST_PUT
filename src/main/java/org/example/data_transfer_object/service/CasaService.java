package org.example.data_transfer_object.service;

import org.example.data_transfer_object.dto.CasaDTO;
import org.example.data_transfer_object.mapper.CasaMapper;
import org.example.data_transfer_object.repository.CasaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CasaService {

    private final CasaRepository casaRepository;
    private final CasaMapper casaMapper;

    public CasaService(CasaRepository casaRepository, CasaMapper casaMapper) {
        this.casaRepository = casaRepository;
        this.casaMapper = casaMapper;
    }

    public List<CasaDTO> findAll() {
        return casaRepository.findAll()
            .stream()
            .map(casaMapper::toDTO)
            .collect(Collectors.toList());
    }

    public Optional<CasaDTO> findById(Long id) {
        return casaRepository.findById(id)
            .map(casaMapper::toDTO);
    }
}
