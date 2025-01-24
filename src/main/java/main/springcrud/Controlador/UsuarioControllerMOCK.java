package main.springcrud.Controlador;

import jakarta.validation.Valid;
import main.springcrud.Modelo.DTOs.Usuario;
import main.springcrud.Modelo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarioCRUD")
public class UsuarioControllerMOCK {
    private final UsuarioRepository repositorioUsuarios;

    @Autowired
    public UsuarioControllerMOCK(UsuarioRepository repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    // GET --> SELECT *
    @GetMapping("/usuarioLIST")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> lista = repositorioUsuarios.findAll();
        return ResponseEntity.ok(lista);
    }

    // GET BY ID --> SELECT BY ID
    @GetMapping("/GET_{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = repositorioUsuarios.findById(id);
        return usuarioOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST --> INSERT
    @PostMapping("/UsuarioPOST")
    public ResponseEntity<Usuario> addUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioPersistido = repositorioUsuarios.save(usuario);
        return ResponseEntity.ok(usuarioPersistido);
    }

    // PUT --> UPDATE
    @PutMapping("/PUT_{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = repositorioUsuarios.findById(id);
        if (usuarioOpt.isPresent()) {
            usuario.setId(id); // Asegurarse de que el ID no cambie
            Usuario usuarioPersistido = repositorioUsuarios.save(usuario);
            return ResponseEntity.ok(usuarioPersistido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/usuarioDELETE_{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer id) {
        repositorioUsuarios.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado");
    }
}