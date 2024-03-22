package com.programandoenjava.desafiomarzo2024.services.impl;

import com.programandoenjava.desafiomarzo2024.exceptions.ResourceNotFoundException;
import com.programandoenjava.desafiomarzo2024.models.dtos.HabitacionDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.CreateHabitacionDto;
import com.programandoenjava.desafiomarzo2024.models.entities.Habitacion;
import com.programandoenjava.desafiomarzo2024.models.mappers.IHabitacionMapper;
import com.programandoenjava.desafiomarzo2024.models.respositories.IHabitacionRepository;
import com.programandoenjava.desafiomarzo2024.services.IHabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitacionServiceImpl implements IHabitacionService {

    private final IHabitacionRepository habitacionRepository;
    private final IHabitacionMapper habitacionMapper;

    @Override
    public List<HabitacionDto> getAllHabitaciones() {
        List<Habitacion> habitaciones = habitacionRepository.findAll();

        return habitaciones.stream()
                .map(habitacionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HabitacionDto> getHabitacionById(Long habitacionId) {
        Optional<Habitacion> habitacion = habitacionRepository.findById(habitacionId);
        return habitacion.map(habitacionMapper::toDto);
    }

    @Override
    public HabitacionDto createHabitacion(CreateHabitacionDto createHabitacionDto) {
        Habitacion nuevaHabitacion = habitacionMapper.toEntity(createHabitacionDto);
        habitacionRepository.save(nuevaHabitacion);
        return habitacionMapper.toDto(nuevaHabitacion);
    }

    @Override
    public HabitacionDto updateHabitacion(Long id, CreateHabitacionDto updatedHabitacionDto) {
        // Verificar si la habitacion existe por el ID
        Optional<Habitacion> optionalHabitacion = habitacionRepository.findById(id);

        if (optionalHabitacion.isPresent()) {
            // Obtener la habitacion existente
            Habitacion existingHabitacion = optionalHabitacion.get();

            // Actualizar las propiedades de la habitacion existente con las proporcionadas en updateHabitacionDto
            existingHabitacion.setCaracteristicas(updatedHabitacionDto.getCaracteristicas());
            existingHabitacion.setNumero(updatedHabitacionDto.getNumero());
            existingHabitacion.setCapacidad(updatedHabitacionDto.getCapacidad());
            existingHabitacion.setPrecio(updatedHabitacionDto.getPrecio());


            // Guardar la habitacion actualizada en la base de datos
            habitacionRepository.save(existingHabitacion);

            // Mapear la habitacion actualizada a DTO
            return habitacionMapper.toDto(existingHabitacion);
        } else {
            throw new ResourceNotFoundException("habitacion", "id", id);
        }

    }

    @Override
    public boolean existsById(Long id) {
        return habitacionRepository.existsById(id);
    }

    @Override
    public void deleteHabitacionById(Long hacitacionId) {
        Optional<Habitacion> habitacion = habitacionRepository.findById(hacitacionId);
        if (habitacion.isPresent()) {
            habitacionRepository.deleteById(habitacion.get().getId());
        } else {
            throw new ResourceNotFoundException("habitacion", "id", hacitacionId);
        }
    }
}
