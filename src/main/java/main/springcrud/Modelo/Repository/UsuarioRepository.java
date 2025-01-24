package main.springcrud.Modelo.Repository;

import main.springcrud.Modelo.DTOs.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
