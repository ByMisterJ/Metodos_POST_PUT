package org.example.data_transfer_object.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "casas")
public class Casa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String fundador;
    
    @Column(nullable = false)
    private String fantasma;
    
    @ManyToOne
    @JoinColumn(name = "jefe_id")
    private Profesor jefe;
    
    @OneToMany(mappedBy = "casa")
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Casa() {
    }

    public Casa(String nombre, String fundador, String fantasma) {
        this.nombre = nombre;
        this.fundador = fundador;
        this.fantasma = fantasma;
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

    public String getFundador() {
        return fundador;
    }

    public void setFundador(String fundador) {
        this.fundador = fundador;
    }

    public String getFantasma() {
        return fantasma;
    }

    public void setFantasma(String fantasma) {
        this.fantasma = fantasma;
    }

    public Profesor getJefe() {
        return jefe;
    }

    public void setJefe(Profesor jefe) {
        this.jefe = jefe;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
