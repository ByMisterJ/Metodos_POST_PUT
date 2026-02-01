package org.example.data_transfer_object.mapper;

import org.example.data_transfer_object.dto.AsignaturaCalificacionDTO;
import org.example.data_transfer_object.dto.EstudianteDTO;
import org.example.data_transfer_object.dto.MascotaDTO;
import org.example.data_transfer_object.entity.Estudiante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstudianteMapper {

    private final MascotaMapper mascotaMapper;
    private final AsignaturaCalificacionMapper asignaturaCalificacionMapper;

    public EstudianteMapper(MascotaMapper mascotaMapper, 
                           AsignaturaCalificacionMapper asignaturaCalificacionMapper) {
        this.mascotaMapper = mascotaMapper;
        this.asignaturaCalificacionMapper = asignaturaCalificacionMapper;
    }

    public EstudianteDTO toDTO(Estudiante estudiante) {
        if (estudiante == null) {
            return null;
        }
        
        String casaNombre = estudiante.getCasa() != null ? 
            estudiante.getCasa().getNombre() : null;
        
        MascotaDTO mascotaDTO = mascotaMapper.toDTO(estudiante.getMascota());
        
        List<AsignaturaCalificacionDTO> asignaturas = estudiante.getEstudianteAsignaturas()
            .stream()
            .map(asignaturaCalificacionMapper::toDTO)
            .collect(Collectors.toList());
        
        return new EstudianteDTO(
            estudiante.getId(),
            estudiante.getNombre(),
            estudiante.getAnyoCurso(),
            estudiante.getFechaNacimiento(),
            casaNombre,
            mascotaDTO,
            asignaturas
        );
    }
}
