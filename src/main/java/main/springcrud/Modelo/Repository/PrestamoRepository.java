package main.springcrud.Modelo.Repository;

import main.springcrud.Modelo.DTOs.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
}
