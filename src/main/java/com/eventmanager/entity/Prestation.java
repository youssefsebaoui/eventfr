package com.eventmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "prestations")
public class Prestation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String categorie;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "proprietaire_id")
    private Utilisateur proprietaire;

    @JsonIgnore
    @ManyToMany(mappedBy = "prestations")
    private List<Pack> packs;

    @JsonIgnore
    @ManyToMany(mappedBy = "prestations")
    private List<Commande> commandes;

    @OneToMany(mappedBy = "prestation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SousPrestation> sousPrestations;

    @Transient
    public Double getPrixTotal() {
        if (sousPrestations == null) return 0.0;

        return sousPrestations.stream()
                .mapToDouble(SousPrestation::getPrix)
                .sum();
    }
}