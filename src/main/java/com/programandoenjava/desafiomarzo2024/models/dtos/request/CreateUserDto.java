package com.programandoenjava.desafiomarzo2024.models.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
public class CreateUserDto {

    @NotEmpty(message = "campo username obligatorio")
    @NotNull
    @UniqueElements
    private String username;

    @NotEmpty(message = "name required")
    @NotNull
    private String nombre;

    @NotEmpty(message = "surname required")
    @NotNull
    private String apellido;

    @Email
    @NotEmpty(message = "email required")
    @NotNull
    private String email;

    @NotEmpty(message = "username required")
    @NotNull
    private String contraseña;
}
