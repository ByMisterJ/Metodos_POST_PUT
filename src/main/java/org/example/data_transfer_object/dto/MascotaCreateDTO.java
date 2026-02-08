package org.example.data_transfer_object.dto;

import jakarta.validation.constraints.NotNull;

public class MascotaCreateDTO {

    @NotNull(message = "El nombre de la mascota no puede estar vacío")
    private String nombre;

    @NotNull(message = "La especie de la mascota no puede estar vacía")
    private String especie;

    // Constructores
    public MascotaCreateDTO() {
    }

    public MascotaCreateDTO(String nombre, String especie) {
        this.nombre = nombre;
        this.especie = especie;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
}