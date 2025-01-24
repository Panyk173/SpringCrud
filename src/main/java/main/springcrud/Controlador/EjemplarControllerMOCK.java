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

    // GET BY ISBN --> SELECT BY ISBN
    @GetMapping("/GET_{isbn}")
    public ResponseEntity<Ejemplar> getEjemplar(@PathVariable Integer isbn) {
        Optional<Ejemplar> ejemplarOpt = repositorioEjemplares.findById(isbn);
        return ejemplarOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST --> INSERT
    @PostMapping("/EjemplarPOST")
    public ResponseEntity<Ejemplar> addEjemplar(@Valid @RequestBody Ejemplar ejemplar) {
        Ejemplar ejemplarPersistido = repositorioEjemplares.save(ejemplar);
        return ResponseEntity.ok(ejemplarPersistido);
    }

    // PUT --> UPDATE
    @PutMapping("/PUT_{isbn}")
    public ResponseEntity<Ejemplar> updateEjemplar(@RequestBody Ejemplar ejemplar, @PathVariable Integer isbn) {
        Optional<Ejemplar> ejemplarOpt = repositorioEjemplares.findById(isbn);
        if (ejemplarOpt.isPresent()) {
            ejemplar.setId(isbn); // Asegurarse de que el ISBN no cambie
            Ejemplar ejemplarPersistido = repositorioEjemplares.save(ejemplar);
            return ResponseEntity.ok(ejemplarPersistido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/ejemplarDELETE_{isbn}")
    public ResponseEntity<String> deleteLibro(@PathVariable Integer isbn) {
        repositorioEjemplares.deleteById(isbn);
        String mensaje = "Libro con ISBN: " + isbn + " borrado";
        return ResponseEntity.ok(mensaje);
    }
}
