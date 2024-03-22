package com.programandoenjava.desafiomarzo2024.controllers;

import com.programandoenjava.desafiomarzo2024.exceptions.BadRequestException;
import com.programandoenjava.desafiomarzo2024.exceptions.ResourceNotFoundException;
import com.programandoenjava.desafiomarzo2024.models.dtos.HabitacionDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.CreateHabitacionDto;
import com.programandoenjava.desafiomarzo2024.services.IHabitacionService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class HabitacionController {

    private final IHabitacionService habitacionService;

    @GetMapping()
    public ResponseEntity<?> getAllHabitaciones() {
        var habitaciones = habitacionService.getAllHabitaciones();
        if (habitaciones == null || habitaciones.isEmpty()) {
            throw new ResourceNotFoundException("habitaciones");
        }
        return new ResponseEntity<>(habitaciones, HttpStatus.OK);
    }

    @GetMapping("/{habitacionId}")
    public ResponseEntity<?> getHabitacionById(@Parameter(description = "ID de la habitacion a buscar")@PathVariable Long habitacionId) {
        var habitacion = habitacionService.getHabitacionById(habitacionId);

        if (habitacion.isEmpty()) {
            throw new ResourceNotFoundException("habitacion", "id", habitacionId);
        }

        return new ResponseEntity<>(habitacion, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createNewRoom(@RequestBody @Valid CreateHabitacionDto createHabitacionDto) {
        HabitacionDto newHabitacion;
        try {
             newHabitacion = habitacionService.createHabitacion(createHabitacionDto);
        } catch (DataAccessException exception) {
            throw new BadRequestException(exception.getMessage());
        }
        return new ResponseEntity<>(newHabitacion, HttpStatus.CREATED);
    }

    @PutMapping("/{habitacionId}")
    public ResponseEntity<?> updateHabitacion(@RequestBody @Valid CreateHabitacionDto habitacionDto, @PathVariable Long habitacionId) {
        try {
            HabitacionDto updatedHabitacionDto = habitacionService.updateHabitacion(habitacionId, habitacionDto);
            return ResponseEntity.ok(updatedHabitacionDto);
        } catch (DataAccessException e){
            throw new BadRequestException(e.getMessage());
        }
    }

}
