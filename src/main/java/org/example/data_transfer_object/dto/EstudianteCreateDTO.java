package org.example.data_transfer_object.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class EstudianteCreateDTO {

    @NotNull(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El apellido no puede estar vacío")
    private String apellido;

    @Min(value = 1, message = "El año de curso debe ser al menos 1")
    @Max(value = 7, message = "El año de curso no puede ser mayor a 7")
    private int anyoCurso;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El ID de la casa es obligatorio")
    private Long casaId;


    @NotNull(message = "La mascota es obligatoria")
    private MascotaCreateDTO mascota;

    // Constructores
    public EstudianteCreateDTO() {
    }

    public EstudianteCreateDTO(String nombre, String apellido, int anyoCurso, LocalDate fechaNacimiento, Long casaId, MascotaCreateDTO mascota) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.anyoCurso = anyoCurso;
        this.fechaNacimiento = fechaNacimiento;
        this.casaId = casaId;
        this.mascota = mascota;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getAnyoCurso() {
        return anyoCurso;
    }

    public void setAnyoCurso(int anyoCurso) {
        this.anyoCurso = anyoCurso;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getCasaId() {
        return casaId;
    }

    public void setCasaId(Long casaId) {
        this.casaId = casaId;
    }

    public MascotaCreateDTO getMascota() {
        return mascota;
    }

    public void setMascota(MascotaCreateDTO mascota) {
        this.mascota = mascota;
    }
}