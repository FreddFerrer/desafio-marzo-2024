package com.programandoenjava.desafiomarzo2024.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsuarioDto {
    private Long id;
    private String username;
    private String nombre;
    private String apellido;
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String type = "Bearer";

    private String rol;
}
