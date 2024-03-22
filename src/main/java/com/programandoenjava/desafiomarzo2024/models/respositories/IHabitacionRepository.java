package com.programandoenjava.desafiomarzo2024.models.respositories;

import com.programandoenjava.desafiomarzo2024.models.entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IHabitacionRepository extends JpaRepository<Habitacion, Long> {

    Optional<Habitacion> findByNumero(Integer numero);

    Collection<?> findByReservaFechaInicioAfter(Date fechaFin);

    List<Habitacion> findByReservaIdIsNullOrReservaFechaFinBefore(Date fechaInicio);
}
