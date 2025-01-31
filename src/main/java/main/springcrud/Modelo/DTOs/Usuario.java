package main.springcrud.Modelo.DTOs;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "El DNI no puede estar vacío")
    @Size(max = 15, message = "El DNI no puede superar los 15 caracteres")
    @Pattern(regexp = "^[0-9]{7,15}$", message = "El DNI debe contener solo números y tener entre 7 y 15 dígitos")
    @Column(name = "dni", nullable = false, length = 15)
    private String dni;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$", message = "El nombre solo puede contener caracteres alfabéticos y espacios")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 100, message = "El email no puede superar los 100 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "El email debe ser una dirección válida de Gmail")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 4, max = 12, message = "La contraseña debe tener entre 4 y 12 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "La contraseña solo puede contener caracteres alfanuméricos")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @NotBlank(message = "El tipo de usuario no puede estar vacío")
    @Pattern(regexp = "^(normal|administrador)$", message = "El tipo de usuario debe ser 'normal' o 'administrador'")
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "penalizacionHasta")
    private LocalDate penalizacionHasta;

    @OneToMany(mappedBy = "usuario")
    private Set<Prestamo> prestamos = new LinkedHashSet<>();
}
