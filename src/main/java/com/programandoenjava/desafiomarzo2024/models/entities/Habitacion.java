package com.programandoenjava.desafiomarzo2024.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "habitaciones")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caracteristicas;

    private Integer numero;

    private Integer capacidad;

    private Double precio;

    @ManyToOne
    @JoinColumn(name = "habitacion_id", nullable=false)
    private Reserva reserva;
}
