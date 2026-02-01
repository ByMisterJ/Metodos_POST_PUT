package org.example.data_transfer_object.service;

import org.example.data_transfer_object.dto.MascotaDTO;
import org.example.data_transfer_object.mapper.MascotaMapper;
import org.example.data_transfer_object.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final MascotaMapper mascotaMapper;

    public MascotaService(MascotaRepository mascotaRepository, MascotaMapper mascotaMapper) {
        this.mascotaRepository = mascotaRepository;
        this.mascotaMapper = mascotaMapper;
    }

    public List<MascotaDTO> findAll() {
        return mascotaRepository.findAll()
            .stream()
            .map(mascotaMapper::toDTO)
            .collect(Collectors.toList());
    }

    public Optional<MascotaDTO> findById(Long id) {
        return mascotaRepository.findById(id)
            .map(mascotaMapper::toDTO);
    }
}
