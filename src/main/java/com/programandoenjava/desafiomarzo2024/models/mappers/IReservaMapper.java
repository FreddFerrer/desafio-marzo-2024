package com.programandoenjava.desafiomarzo2024.models.mappers;

import com.programandoenjava.desafiomarzo2024.models.dtos.ReservaDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.CreateReservaDto;
import com.programandoenjava.desafiomarzo2024.models.entities.Reserva;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IReservaMapper {

    ReservaDto toDto(Reserva reserva);

    Reserva toEntity(CreateReservaDto createReservaDto);
}
