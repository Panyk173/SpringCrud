package main.springcrud.Modelo.DTOs;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @NotBlank(message = "El ISBN no puede estar vacío")
    @Pattern(
            regexp = "^(97[89][- ]?)?\\d{1,5}[- ]?\\d{1,7}[- ]?\\d{1,6}[- ]?\\d$",
            message = "El ISBN debe tener el formato correcto, ejemplo: 978-0-596-52068-7"
    )
    @Size(max = 20, message = "El ISBN no puede superar los 20 caracteres")
    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @NotBlank(message = "El título no puede estar vacío")
    @Pattern(
            regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ\\s]+$",
            message = "El título solo puede contener caracteres alfanuméricos y espacios"
    )
    @Size(max = 200, message = "El título no puede superar los 200 caracteres")
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @NotBlank(message = "El autor no puede estar vacío")
    @Pattern(
            regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ\\s]+$",
            message = "El autor solo puede contener caracteres alfanuméricos y espacios"
    )
    @Size(max = 100, message = "El autor no puede superar los 100 caracteres")
    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    @OneToMany(mappedBy = "isbn", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Ejemplar> ejemplars = new LinkedHashSet<>();
}
