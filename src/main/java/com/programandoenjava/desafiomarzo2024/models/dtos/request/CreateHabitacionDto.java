package com.programandoenjava.desafiomarzo2024.models.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateHabitacionDto {

    @NotEmpty(message = "El campo 'caracteristicas' no debe estar vacio")
    private String caracteristicas;

    @NotNull(message = "el campo 'numero' no puede ser nulo")
    //@NotEmpty(message = "el campo 'numero' no debe estar vacio")
    @Min(value = 1, message = "el numero debe ser mayor a 0")
    private Integer numero;

    @NotNull(message = "el campo 'capacidad' no puede ser nulo")
    //@NotEmpty(message = "el campo 'capacidad' no debe estar vacio")
    @Min(value = 1, message = "La capacidad debe ser mayor que 0")
    private Integer capacidad;

    @NotNull(message = "el campo 'precio' no puede ser nulo")
    //@NotEmpty(message = "el campo 'precio' no debe estar vacio")
    @Min(value = 1, message = "El precio debe ser mayor que 0")
    private Double precio;
}
