package com.programandoenjava.desafiomarzo2024.models.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    @NotNull(message = "username empty")
    private String username;

    @NotNull
    private String password;
}
