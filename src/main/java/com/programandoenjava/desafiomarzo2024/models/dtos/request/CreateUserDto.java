package com.programandoenjava.desafiomarzo2024.models.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateUserDto {

    @NotEmpty(message = "campo 'username' no debe estar vacio")
    @NotNull(message = "el campo 'username' no puede ser nulo")
    private String username;

    @NotEmpty(message = "campo 'nombre' no debe estar vacio")
    @NotNull(message = "el campo 'nombre' no puede ser nulo")
    private String nombre;

    @NotEmpty(message = "campo 'apellido' no debe estar vacio")
    @NotNull(message = "el campo 'apellido' no puede ser nulo")
    private String apellido;

    @Email
    @NotEmpty(message = "campo 'email' no debe estar vacio")
    @NotNull(message = "el campo 'email' no puede ser nulo")
    private String email;

    @NotEmpty(message = "campo 'password' no debe estar vacio")
    @NotNull(message = "el campo 'password' no puede ser nulo")
    private String password;
}
