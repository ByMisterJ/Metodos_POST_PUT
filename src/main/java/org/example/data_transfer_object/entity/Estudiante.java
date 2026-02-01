package org.example.data_transfer_object.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiantes")
public class Estudiante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(name = "anyo_curso", nullable = false)
    private int anyoCurso;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    
    @ManyToOne
    @JoinColumn(name = "casa_id")
    private Casa casa;
    
    @OneToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;
    
    @OneToMany(mappedBy = "estudiante")
    private List<EstudianteAsignatura> estudianteAsignaturas = new ArrayList<>();

    public Estudiante() {
    }

    public Estudiante(String nombre, int anyoCurso, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.anyoCurso = anyoCurso;
        this.fechaNacimiento = fechaNacimiento;
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

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public List<EstudianteAsignatura> getEstudianteAsignaturas() {
        return estudianteAsignaturas;
    }

    public void setEstudianteAsignaturas(List<EstudianteAsignatura> estudianteAsignaturas) {
        this.estudianteAsignaturas = estudianteAsignaturas;
    }
}
