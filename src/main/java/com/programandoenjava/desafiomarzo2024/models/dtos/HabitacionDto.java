package com.programandoenjava.desafiomarzo2024.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.programandoenjava.desafiomarzo2024.models.entities.Reserva;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitacionDto {

    private Long id;
    private String caracteristicas;
    private Integer numero;
    private Integer capacidad;
    private Double precio;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Reserva reserva;
}
