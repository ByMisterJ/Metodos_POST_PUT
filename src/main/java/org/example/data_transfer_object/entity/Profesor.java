package org.example.data_transfer_object.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "profesores")
public class Profesor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String asignatura;
    
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    public Profesor() {
    }

    public Profesor(String nombre, String asignatura, LocalDate fechaInicio) {
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
