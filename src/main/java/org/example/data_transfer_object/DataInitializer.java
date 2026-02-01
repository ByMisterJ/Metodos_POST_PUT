package org.example.data_transfer_object;

import org.example.data_transfer_object.entity.*;
import org.example.data_transfer_object.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProfesorRepository profesorRepository;
    private final CasaRepository casaRepository;
    private final EstudianteRepository estudianteRepository;
    private final MascotaRepository mascotaRepository;
    private final AsignaturaRepository asignaturaRepository;

    public DataInitializer(ProfesorRepository profesorRepository,
                          CasaRepository casaRepository,
                          EstudianteRepository estudianteRepository,
                          MascotaRepository mascotaRepository,
                          AsignaturaRepository asignaturaRepository) {
        this.profesorRepository = profesorRepository;
        this.casaRepository = casaRepository;
        this.estudianteRepository = estudianteRepository;
        this.mascotaRepository = mascotaRepository;
        this.asignaturaRepository = asignaturaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create Profesores
        Profesor mcgonagall = new Profesor("Minerva McGonagall", "Transformaciones", LocalDate.of(1956, 1, 1));
        Profesor snape = new Profesor("Severus Snape", "Pociones", LocalDate.of(1981, 9, 1));
        Profesor flitwick = new Profesor("Filius Flitwick", "Encantamientos", LocalDate.of(1975, 9, 1));
        Profesor sprout = new Profesor("Pomona Sprout", "Herbología", LocalDate.of(1974, 9, 1));
        
        profesorRepository.save(mcgonagall);
        profesorRepository.save(snape);
        profesorRepository.save(flitwick);
        profesorRepository.save(sprout);

        // Create Casas
        Casa gryffindor = new Casa("Gryffindor", "Godric Gryffindor", "Casi Decapitado Nick");
        gryffindor.setJefe(mcgonagall);
        
        Casa slytherin = new Casa("Slytherin", "Salazar Slytherin", "El Barón Sanguinario");
        slytherin.setJefe(snape);
        
        Casa ravenclaw = new Casa("Ravenclaw", "Rowena Ravenclaw", "La Dama Gris");
        ravenclaw.setJefe(flitwick);
        
        Casa hufflepuff = new Casa("Hufflepuff", "Helga Hufflepuff", "El Fraile Gordo");
        hufflepuff.setJefe(sprout);
        
        casaRepository.save(gryffindor);
        casaRepository.save(slytherin);
        casaRepository.save(ravenclaw);
        casaRepository.save(hufflepuff);

        // Create Mascotas
        Mascota hedwig = new Mascota("Hedwig", "Lechuza");
        Mascota crookshanks = new Mascota("Crookshanks", "Gato");
        Mascota scabbers = new Mascota("Scabbers", "Rata");
        
        mascotaRepository.save(hedwig);
        mascotaRepository.save(crookshanks);
        mascotaRepository.save(scabbers);

        // Create Estudiantes
        Estudiante harry = new Estudiante("Harry Potter", 5, LocalDate.of(1980, 7, 31));
        harry.setCasa(gryffindor);
        harry.setMascota(hedwig);
        
        Estudiante hermione = new Estudiante("Hermione Granger", 5, LocalDate.of(1979, 9, 19));
        hermione.setCasa(gryffindor);
        hermione.setMascota(crookshanks);
        
        Estudiante ron = new Estudiante("Ron Weasley", 5, LocalDate.of(1980, 3, 1));
        ron.setCasa(gryffindor);
        ron.setMascota(scabbers);
        
        estudianteRepository.save(harry);
        estudianteRepository.save(hermione);
        estudianteRepository.save(ron);

        // Create Asignaturas
        Asignatura pociones = new Asignatura("Pociones", "Mazmorra 1", true);
        pociones.setProfesor(snape);
        
        Asignatura transformaciones = new Asignatura("Transformaciones", "Aula 34", true);
        transformaciones.setProfesor(mcgonagall);
        
        Asignatura encantamientos = new Asignatura("Encantamientos", "Aula 99", true);
        encantamientos.setProfesor(flitwick);
        
        Asignatura herbologia = new Asignatura("Herbología", "Invernadero 3", true);
        herbologia.setProfesor(sprout);
        
        asignaturaRepository.save(pociones);
        asignaturaRepository.save(transformaciones);
        asignaturaRepository.save(encantamientos);
        asignaturaRepository.save(herbologia);

        // Create EstudianteAsignatura relationships
        EstudianteAsignatura harryPociones = new EstudianteAsignatura(harry, pociones, 7.5);
        EstudianteAsignatura harryTransformaciones = new EstudianteAsignatura(harry, transformaciones, 8.0);
        EstudianteAsignatura harryEncantamientos = new EstudianteAsignatura(harry, encantamientos, 8.5);
        
        EstudianteAsignatura hermionePociones = new EstudianteAsignatura(hermione, pociones, 9.8);
        EstudianteAsignatura hermioneTransformaciones = new EstudianteAsignatura(hermione, transformaciones, 9.9);
        EstudianteAsignatura hermioneEncantamientos = new EstudianteAsignatura(hermione, encantamientos, 10.0);
        EstudianteAsignatura hermioneHerbologia = new EstudianteAsignatura(hermione, herbologia, 9.7);
        
        EstudianteAsignatura ronPociones = new EstudianteAsignatura(ron, pociones, 6.5);
        EstudianteAsignatura ronTransformaciones = new EstudianteAsignatura(ron, transformaciones, 7.0);
        EstudianteAsignatura ronEncantamientos = new EstudianteAsignatura(ron, encantamientos, 7.5);
        
        harry.getEstudianteAsignaturas().add(harryPociones);
        harry.getEstudianteAsignaturas().add(harryTransformaciones);
        harry.getEstudianteAsignaturas().add(harryEncantamientos);
        
        hermione.getEstudianteAsignaturas().add(hermionePociones);
        hermione.getEstudianteAsignaturas().add(hermioneTransformaciones);
        hermione.getEstudianteAsignaturas().add(hermioneEncantamientos);
        hermione.getEstudianteAsignaturas().add(hermioneHerbologia);
        
        ron.getEstudianteAsignaturas().add(ronPociones);
        ron.getEstudianteAsignaturas().add(ronTransformaciones);
        ron.getEstudianteAsignaturas().add(ronEncantamientos);
        
        estudianteRepository.save(harry);
        estudianteRepository.save(hermione);
        estudianteRepository.save(ron);

        System.out.println("Database initialized with sample Hogwarts data!");
    }
}
