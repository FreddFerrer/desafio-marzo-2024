package com.programandoenjava.desafiomarzo2024.services.impl;

import com.programandoenjava.desafiomarzo2024.exceptions.ResourceNotFoundException;
import com.programandoenjava.desafiomarzo2024.models.dtos.HabitacionDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.ReservaDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.CreateReservaDto;
import com.programandoenjava.desafiomarzo2024.models.entities.Habitacion;
import com.programandoenjava.desafiomarzo2024.models.entities.Reserva;
import com.programandoenjava.desafiomarzo2024.models.enums.EstadoReserva;
import com.programandoenjava.desafiomarzo2024.models.mappers.IHabitacionMapper;
import com.programandoenjava.desafiomarzo2024.models.mappers.IReservaMapper;
import com.programandoenjava.desafiomarzo2024.models.respositories.IHabitacionRepository;
import com.programandoenjava.desafiomarzo2024.models.respositories.IReservaRepository;
import com.programandoenjava.desafiomarzo2024.services.IReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservsServiceImpl implements IReservaService {

    private final IHabitacionRepository habitacionRepository;
    private final IReservaRepository reservaRepository;
    private final IHabitacionMapper habitacionMapper;
    private final IReservaMapper reservaMapper;

    @Override
    public List<ReservaDto> getAllReservas() {
        List<Reserva> reservas = reservaRepository.findAll();

        return reservas.stream()
                .map(reservaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HabitacionDto> getAvailableHabitaciones(Date fechaInicio, Date fechaFin) {
        // Filtrar habitaciones sin reserva o con reserva fuera del periodo (igual al ejemplo anterior)
        List<Habitacion> disponibles = habitacionRepository.findByReservaIdIsNullOrReservaFechaFinBefore(fechaInicio);
        disponibles.retainAll(habitacionRepository.findByReservaFechaInicioAfter(fechaFin));

        // Convertir las entidades a DTO
        List<HabitacionDto> dtos = new ArrayList<>();
        for (Habitacion habitacion : disponibles) {
            dtos.add(habitacionMapper.toDto(habitacion));
        }

        return dtos;
    }

    @Override
    public ReservaDto createReserva(CreateReservaDto reserva) {
        // Validar la reserva (fechas, disponibilidad de habitación, etc.)
        // Asociar la reserva a la habitación seleccionada (setear el campo habitacion en la reserva)
        Reserva nuevaReserva = reservaMapper.toEntity(reserva);
        reservaRepository.save(nuevaReserva);
        return reservaMapper.toDto(nuevaReserva);
    }

    @Override
    public Optional<ReservaDto> getReservaById(Long reservaId) {
        Optional<Reserva> reserva = reservaRepository.findById(reservaId);
        return reserva.map(reservaMapper::toDto);
    }

    @Override
    public void cancelReserva(Long reservaId) {
        reservaRepository.findById(reservaId).ifPresent(reserva -> {
            reserva.setEstado(EstadoReserva.CANCELADA);
            reservaRepository.save(reserva);
        });
    }
}
