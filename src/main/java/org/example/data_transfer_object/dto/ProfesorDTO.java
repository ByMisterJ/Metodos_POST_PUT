package org.example.data_transfer_object.dto;

import java.time.LocalDate;

public class ProfesorDTO {
    private Long id;
    private String nombre;
    private String asignatura;
    private LocalDate fechaInicio;

    public ProfesorDTO() {
    }

    public ProfesorDTO(Long id, String nombre, String asignatura, LocalDate fechaInicio) {
        this.id = id;
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.fechaInicio = fechaInicio;
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

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
