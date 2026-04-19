package com.eventmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sous_services")
public class SousService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "prestation_id")
    private Prestation prestation;
}