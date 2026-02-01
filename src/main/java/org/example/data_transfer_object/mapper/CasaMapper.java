package org.example.data_transfer_object.mapper;

import org.example.data_transfer_object.dto.CasaDTO;
import org.example.data_transfer_object.dto.ProfesorDTO;
import org.example.data_transfer_object.entity.Casa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CasaMapper {

    private final ProfesorMapper profesorMapper;

    public CasaMapper(ProfesorMapper profesorMapper) {
        this.profesorMapper = profesorMapper;
    }

    public CasaDTO toDTO(Casa casa) {
        if (casa == null) {
            return null;
        }
        
        ProfesorDTO jefeDTO = profesorMapper.toDTO(casa.getJefe());
        
        List<String> estudiantesNombres = casa.getEstudiantes()
            .stream()
            .map(estudiante -> estudiante.getNombre())
            .collect(Collectors.toList());
        
        return new CasaDTO(
            casa.getId(),
            casa.getNombre(),
            casa.getFundador(),
            casa.getFantasma(),
            jefeDTO,
            estudiantesNombres
        );
    }
}
