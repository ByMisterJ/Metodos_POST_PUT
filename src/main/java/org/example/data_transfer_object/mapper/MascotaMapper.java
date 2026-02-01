package org.example.data_transfer_object.mapper;

import org.example.data_transfer_object.dto.MascotaDTO;
import org.example.data_transfer_object.entity.Mascota;
import org.springframework.stereotype.Component;

@Component
public class MascotaMapper {

    public MascotaDTO toDTO(Mascota mascota) {
        if (mascota == null) {
            return null;
        }
        String estudianteNombre = mascota.getEstudiante() != null ? 
            mascota.getEstudiante().getNombre() : null;
        
        return new MascotaDTO(
            mascota.getId(),
            mascota.getNombre(),
            mascota.getEspecie(),
            estudianteNombre
        );
    }
}
