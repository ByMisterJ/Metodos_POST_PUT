package org.example.data_transfer_object.mapper;

import org.example.data_transfer_object.dto.AsignaturaDTO;
import org.example.data_transfer_object.entity.Asignatura;
import org.springframework.stereotype.Component;

@Component
public class AsignaturaMapper {

    public AsignaturaDTO toDTO(Asignatura asignatura) {
        if (asignatura == null) {
            return null;
        }
        
        String profesorNombre = asignatura.getProfesor() != null ? 
            asignatura.getProfesor().getNombre() : null;
        
        return new AsignaturaDTO(
            asignatura.getId(),
            asignatura.getNombre(),
            asignatura.getAula(),
            asignatura.getObligatoria(),
            profesorNombre
        );
    }
}
