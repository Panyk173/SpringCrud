package main.springcrud.Modelo.Repository;

import main.springcrud.Modelo.DTOs.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrosRepository extends JpaRepository<Libro, String> {
}
