package com.programandoenjava.desafiomarzo2024.services;

import com.programandoenjava.desafiomarzo2024.models.dtos.HabitacionDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.ReservaDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.CreateReservaDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IReservaService {

    List<ReservaDto> getAllReservas();
    List<HabitacionDto> getAvailableHabitaciones(Date fechaInicio, Date fechaFin);

    ReservaDto createReserva(CreateReservaDto reserva);

    Optional<ReservaDto> getReservaById(Long reservaId);

    void cancelReserva(Long reservaId);
}
