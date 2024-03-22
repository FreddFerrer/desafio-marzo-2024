package com.programandoenjava.desafiomarzo2024.models.mappers;

import com.programandoenjava.desafiomarzo2024.models.dtos.HabitacionDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.CreateHabitacionDto;
import com.programandoenjava.desafiomarzo2024.models.entities.Habitacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IHabitacionMapper {

    HabitacionDto toDto(Habitacion habitacion);

    Habitacion toEntity(CreateHabitacionDto createHabitacionDto);
}
