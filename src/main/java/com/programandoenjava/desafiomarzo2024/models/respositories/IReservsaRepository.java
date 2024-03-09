package com.programandoenjava.desafiomarzo2024.models.respositories;

import com.programandoenjava.desafiomarzo2024.models.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservsaRepository extends JpaRepository<Reserva, Long> {
}
