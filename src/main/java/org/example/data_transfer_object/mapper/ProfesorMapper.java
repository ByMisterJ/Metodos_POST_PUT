package org.example.data_transfer_object.mapper;

import org.example.data_transfer_object.dto.ProfesorDTO;
import org.example.data_transfer_object.entity.Profesor;
import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {

    public ProfesorDTO toDTO(Profesor profesor) {
        if (profesor == null) {
            return null;
        }
        return new ProfesorDTO(
            profesor.getId(),
            profesor.getNombre(),
            profesor.getAsignatura(),
            profesor.getFechaInicio()
        );
    }
}
