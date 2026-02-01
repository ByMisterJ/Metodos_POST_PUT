package org.example.data_transfer_object.dto;

import java.time.LocalDate;
import java.util.List;

public class EstudianteDTO {
    private Long id;
    private String nombre;
    private int anyoCurso;
    private LocalDate fechaNacimiento;
    private String casa;
    private MascotaDTO mascota;
    private List<AsignaturaCalificacionDTO> asignaturas;

    public EstudianteDTO() {
    }

    public EstudianteDTO(Long id, String nombre, int anyoCurso, LocalDate fechaNacimiento, 
                        String casa, MascotaDTO mascota, List<AsignaturaCalificacionDTO> asignaturas) {
        this.id = id;
        this.nombre = nombre;
        this.anyoCurso = anyoCurso;
        this.fechaNacimiento = fechaNacimiento;
        this.casa = casa;
        this.mascota = mascota;
        this.asignaturas = asignaturas;
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

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public MascotaDTO getMascota() {
        return mascota;
    }

    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
    }

    public List<AsignaturaCalificacionDTO> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<AsignaturaCalificacionDTO> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
