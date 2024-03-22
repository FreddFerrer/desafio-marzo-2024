package com.programandoenjava.desafiomarzo2024.controllers;

import com.programandoenjava.desafiomarzo2024.exceptions.BadRequestException;
import com.programandoenjava.desafiomarzo2024.exceptions.ResourceNotFoundException;
import com.programandoenjava.desafiomarzo2024.models.dtos.HabitacionDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.ReservaDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.CreateReservaDto;
import com.programandoenjava.desafiomarzo2024.services.IReservaService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final IReservaService reservaService;

    // Obtiene todas las reservas
    @GetMapping()
    public ResponseEntity<?> getAllReservas() {
        var reservas = reservaService.getAllReservas();
        if (reservas == null || reservas.isEmpty()) {
            throw new ResourceNotFoundException("reservas");
        }
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    // Obtiene las habitaciones disponibles para un período de tiempo
    @GetMapping("/disponibles")
    public ResponseEntity<?> getAvailableHabitaciones(
            @Parameter(description = "Fecha de inicio del período") @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @Parameter(description = "Fecha de fin del período") @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {

        List<HabitacionDto> disponibles = reservaService.getAvailableHabitaciones(fechaInicio, fechaFin);

        if (disponibles.isEmpty()) {
            throw new ResourceNotFoundException("habitaciones disponibles");
        }

        return new ResponseEntity<>(disponibles, HttpStatus.OK);
    }

    // Obtiene una reserva por ID
    @GetMapping("/{reservaId}")
    public ResponseEntity<?> getReservaById(@PathVariable Long reservaId) {
        var reserva = reservaService.getReservaById(reservaId);
        if (reserva.isEmpty()) {
            throw new ResourceNotFoundException("reserva", "id", reservaId);
        }
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    // Crea una nueva reserva
    @PostMapping("/new")
    public ResponseEntity<?> createReserva(@RequestBody @Valid CreateReservaDto createReservaDto) {
        ReservaDto newReserva;
        try {
            newReserva = reservaService.createReserva(createReservaDto);
        } catch (DataAccessException exception) {
            throw new BadRequestException(exception.getMessage());
        }
        return new ResponseEntity<>(newReserva, HttpStatus.CREATED);
    }


    // Cancela una reserva por ID
    @PutMapping("/cancelar/{reservaId}")
    public ResponseEntity<?> cancelarReserva(@PathVariable Long reservaId) {
        reservaService.cancelReserva(reservaId);
        return ResponseEntity.noContent().build();
    }
}
