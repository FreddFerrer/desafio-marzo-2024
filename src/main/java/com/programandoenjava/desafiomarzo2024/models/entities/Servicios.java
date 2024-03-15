package com.programandoenjava.desafiomarzo2024.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "servicios")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ServiciosPopulares;

}
