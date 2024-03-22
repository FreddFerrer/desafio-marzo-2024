package com.programandoenjava.desafiomarzo2024.models.entities;

import com.programandoenjava.desafiomarzo2024.models.enums.EstadoReserva;
import com.programandoenjava.desafiomarzo2024.models.enums.RolEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="reserva")
    private Set<Habitacion> habitaciones;

    private Date fechaInicio;

    private Date fechaFin;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
