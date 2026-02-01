package org.example.data_transfer_object.dto;

public class AsignaturaCalificacionDTO {
    private String asignatura;
    private Double calificacion;

    public AsignaturaCalificacionDTO() {
    }

    public AsignaturaCalificacionDTO(String asignatura, Double calificacion) {
        this.asignatura = asignatura;
        this.calificacion = calificacion;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
}
