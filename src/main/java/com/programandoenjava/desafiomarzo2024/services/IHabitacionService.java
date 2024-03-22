package com.programandoenjava.desafiomarzo2024.services;

import com.programandoenjava.desafiomarzo2024.models.dtos.HabitacionDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.CreateHabitacionDto;

import java.util.List;
import java.util.Optional;

public interface IHabitacionService {

    List<HabitacionDto> getAllHabitaciones();
    Optional<HabitacionDto> getHabitacionById(Long habitacionId);
    HabitacionDto createHabitacion(CreateHabitacionDto createHabitacionDto);
    HabitacionDto updateHabitacion(Long id, CreateHabitacionDto updatedHabitacionDto);
    boolean existsById(Long id);
    void deleteHabitacionById(Long hacitacionId);
}
