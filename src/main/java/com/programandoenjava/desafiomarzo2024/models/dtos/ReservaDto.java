package com.programandoenjava.desafiomarzo2024.models.dtos;

import com.programandoenjava.desafiomarzo2024.models.enums.EstadoReserva;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReservaDto {
    private Long id;
    private Date fechaInicio;
    private Date fechaFin;
    private List<HabitacionDto> habitaciones;
    private EstadoReserva estado;
    private UsuarioDto usuario;
}
