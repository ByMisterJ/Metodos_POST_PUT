package org.example.data_transfer_object.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "asignaturas")
public class Asignatura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String aula;
    
    @Column(nullable = false)
    private Boolean obligatoria;
    
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;
    
    @OneToMany(mappedBy = "asignatura")
    private List<EstudianteAsignatura> estudianteAsignaturas = new ArrayList<>();

    public Asignatura() {
    }

    public Asignatura(String nombre, String aula, Boolean obligatoria) {
        this.nombre = nombre;
        this.aula = aula;
        this.obligatoria = obligatoria;
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

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Boolean getObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(Boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<EstudianteAsignatura> getEstudianteAsignaturas() {
        return estudianteAsignaturas;
    }

    public void setEstudianteAsignaturas(List<EstudianteAsignatura> estudianteAsignaturas) {
        this.estudianteAsignaturas = estudianteAsignaturas;
    }
}
