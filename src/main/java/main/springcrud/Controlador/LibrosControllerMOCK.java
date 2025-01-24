package main.springcrud.Controlador;

import jakarta.validation.Valid;
import main.springcrud.Modelo.DTOs.Libro;
import main.springcrud.Modelo.Repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libroCRUD")
public class LibrosControllerMOCK {
    private final LibrosRepository repositorioLibros;

    @Autowired
    public LibrosControllerMOCK(LibrosRepository repositorioLibros) {
        this.repositorioLibros = repositorioLibros;
    }

    // GET --> SELECT *
    @GetMapping("/libroLIST")
    public ResponseEntity<List<Libro>> getLibros() {
        List<Libro> lista = repositorioLibros.findAll();
        return ResponseEntity.ok(lista);
    }

    // GET BY ISBN --> SELECT BY ISBN
    @GetMapping("/GET_{isbn}")
    @Cacheable
    public ResponseEntity<Libro> getLibro(@PathVariable String isbn) {
        Optional<Libro> libroOpt = repositorioLibros.findById(isbn);
        return libroOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST --> INSERT
    @PostMapping("/LibroPOST")
    public ResponseEntity<Libro> addLibro(@Valid @RequestBody Libro libro) {
        Libro libroPersistido = repositorioLibros.save(libro);
        return ResponseEntity.ok(libroPersistido);
    }

    // PUT --> UPDATE
    @PutMapping("/PUT_{isbn}")
    public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro, @PathVariable String isbn) {
        Optional<Libro> libroOpt = repositorioLibros.findById(isbn);
        if (libroOpt.isPresent()) {
            libro.setIsbn(isbn); // Asegurarse de que el ISBN no cambie
            Libro libroPersistido = repositorioLibros.save(libro);
            return ResponseEntity.ok(libroPersistido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/libroDELETE_{isbn}")
    public ResponseEntity<String> deleteLibro(@PathVariable String isbn) {
        repositorioLibros.deleteById(isbn);
        String mensaje = "Libro con ISBN: " + isbn + " borrado";
        return ResponseEntity.ok(mensaje);
    }
}