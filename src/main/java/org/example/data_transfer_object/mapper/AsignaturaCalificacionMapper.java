package org.example.data_transfer_object.mapper;

import org.example.data_transfer_object.dto.AsignaturaCalificacionDTO;
import org.example.data_transfer_object.entity.EstudianteAsignatura;
import org.springframework.stereotype.Component;

@Component
public class AsignaturaCalificacionMapper {

    public AsignaturaCalificacionDTO toDTO(EstudianteAsignatura estudianteAsignatura) {
        if (estudianteAsignatura == null || estudianteAsignatura.getAsignatura() == null) {
            return null;
        }
        return new AsignaturaCalificacionDTO(
            estudianteAsignatura.getAsignatura().getNombre(),
            estudianteAsignatura.getCalificacion()
        );
    }
}
