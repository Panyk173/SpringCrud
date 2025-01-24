package main.springcrud.Modelo.Repository;

import main.springcrud.Modelo.DTOs.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {
}
