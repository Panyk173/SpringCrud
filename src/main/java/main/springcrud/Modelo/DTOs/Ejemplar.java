package main.springcrud.Modelo.DTOs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "ejemplar")
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    // Relacion con Libro (ya cumple con "no nulo" por @NotNull y la definicion de la columna)
    @NotNull(message = "El ISBN no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "isbn", nullable = false)
    private Libro isbn;

    // Validacion para "estado": solo valores permitidos
    @NotNull(message = "El estado no puede ser nulo")
    @Pattern(
            regexp = "^(disponible|prestado|dañado)$",
            message = "Estado inválido. Valores permitidos: disponible, prestado, dañado"
    )
    @ColumnDefault("'disponible'") // Asegurate de que coincida con el valor en minusculas del regex
    @Lob
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "ejemplar")
    private Set<Prestamo> prestamos = new LinkedHashSet<>();
}