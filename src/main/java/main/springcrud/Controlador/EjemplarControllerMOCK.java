package main.springcrud.Controlador;

import jakarta.validation.Valid;
import main.springcrud.Modelo.DTOs.Ejemplar;
import main.springcrud.Modelo.Repository.EjemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ejemplarCRUD")
public class EjemplarControllerMOCK {
    private final EjemplarRepository repositorioEjemplares;

    @Autowired
    public EjemplarControllerMOCK(EjemplarRepository repositorioEjemplares) {
        this.repositorioEjemplares = repositorioEjemplares;
    }

    // GET --> SELECT *
    @GetMapping("/ejemplarLIST")
    public ResponseEntity<List<Ejemplar>> getEjemplares() {
        List<Ejemplar> lista = repositorioEjemplares.findAll();
        return ResponseEntity.ok(lista);
    }

    // GET BY ID --> SELECT BY ID
    @GetMapping("/GET_{id}")
    public ResponseEntity<Ejemplar> getEjemplar(@PathVariable Integer id) {
        Optional<Ejemplar> ejemplarOpt = repositorioEjemplares.findById(id);
        return ejemplarOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST --> INSERT
    @PostMapping("/EjemplarPOST")
    public ResponseEntity<Ejemplar> addEjemplar(@Valid @RequestBody Ejemplar ejemplar) {
        Ejemplar ejemplarPersistido = repositorioEjemplares.save(ejemplar);
        return ResponseEntity.ok(ejemplarPersistido);
    }

    // PUT --> UPDATE
    @PutMapping("/PUT_{id}")
    public ResponseEntity<Ejemplar> updateEjemplar(@RequestBody Ejemplar ejemplar, @PathVariable Integer id) {
        Optional<Ejemplar> ejemplarOpt = repositorioEjemplares.findById(id);
        if (ejemplarOpt.isPresent()) {
            ejemplar.setId(id); // Asegurarse de que el ISBN no cambie
            Ejemplar ejemplarPersistido = repositorioEjemplares.save(ejemplar);
            return ResponseEntity.ok(ejemplarPersistido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/ejemplarDELETE_{id}")
    public ResponseEntity<String> deleteLibro(@PathVariable Integer id) {
        repositorioEjemplares.deleteById(id);
        String mensaje = "Libro con id: " + id + " borrado";
        return ResponseEntity.ok(mensaje);
    }
}
