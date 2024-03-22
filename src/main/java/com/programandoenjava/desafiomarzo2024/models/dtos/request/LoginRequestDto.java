package com.programandoenjava.desafiomarzo2024.models.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    @NotEmpty(message = "campo 'username' no debe estar vacio")
    @NotNull(message = "el campo 'username' no puede ser nulo")
    private String username;

    @NotEmpty(message = "campo 'password' no debe estar vacio")
    @NotNull(message = "el campo 'password' no puede ser nulo")
    private String password;
}
