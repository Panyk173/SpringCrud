package main.springcrud.Controlador;


import jakarta.validation.Valid;
import main.springcrud.Modelo.DTOs.Prestamo;
import main.springcrud.Modelo.Repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamoCRUD")
public class PrestamoControllerMOCK {
    private final PrestamoRepository repositorioPrestamos;

    @Autowired
    public PrestamoControllerMOCK(PrestamoRepository repositorioPrestamos) {
        this.repositorioPrestamos = repositorioPrestamos;
    }

    // GET --> SELECT *
    @GetMapping("/prestamoLIST")
    public ResponseEntity<List<Prestamo>> getPrestamos() {
        List<Prestamo> lista = repositorioPrestamos.findAll();
        return ResponseEntity.ok(lista);
    }

    // GET BY ID --> SELECT BY ID
    @GetMapping("/GET_{id}")
    public ResponseEntity<Prestamo> getPrestamo(@PathVariable Integer id) {
        Optional<Prestamo> prestamoOpt = repositorioPrestamos.findById(id);
        return prestamoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST --> INSERT
    @PostMapping("/PrestamoPOST")
    public ResponseEntity<Prestamo> addPrestamo(@Valid @RequestBody Prestamo prestamo) {
        Prestamo prestamoPersistido = repositorioPrestamos.save(prestamo);
        return ResponseEntity.ok(prestamoPersistido);
    }

    // PUT --> UPDATE
    @PutMapping("/PUT_{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@RequestBody Prestamo prestamo, @PathVariable Integer id) {
        Optional<Prestamo> prestamoOpt = repositorioPrestamos.findById(id);
        if (prestamoOpt.isPresent()) {
            prestamo.setId(id); // Asegurarse de que el ID no cambie
            Prestamo prestamoPersistido = repositorioPrestamos.save(prestamo);
            return ResponseEntity.ok(prestamoPersistido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/prestamoDELETE_{id}")
    public ResponseEntity<String> deletePrestamo(@PathVariable Integer id) {
        repositorioPrestamos.deleteById(id);
        return ResponseEntity.ok("Prestamo eliminado");
    }
}
