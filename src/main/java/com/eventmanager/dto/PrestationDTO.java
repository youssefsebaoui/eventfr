package com.eventmanager.dto;

import lombok.Data;
import java.util.List;

@Data
public class PrestationDTO {

    private Long id;
    private String nom;
    private String description;
    private String categorie;
    private String statut;

    private String proprietaireId;

    private List<SousPrestationDTO> sousPrestations;
    private Double prixTotal;
}