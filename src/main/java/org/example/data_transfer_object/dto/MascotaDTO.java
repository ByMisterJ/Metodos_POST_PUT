package org.example.data_transfer_object.dto;

public class MascotaDTO {
    private Long id;
    private String nombre;
    private String especie;
    private String estudiante;

    public MascotaDTO() {
    }

    public MascotaDTO(Long id, String nombre, String especie, String estudiante) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.estudiante = estudiante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }
}
