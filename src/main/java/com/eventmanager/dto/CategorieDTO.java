package com.eventmanager.dto;

public class CategorieDTO {

    private Long id;
    private String name;
    private String description;
    private Long proprietaireId;

    // ✅ Ajouter ces deux champs
    private Long typeEvenementId;
    private String typeEvenementLibelle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProprietaireId() {
        return proprietaireId;
    }

    public void setProprietaireId(Long proprietaireId) {
        this.proprietaireId = proprietaireId;
    }

    public Long getTypeEvenementId() {
        return typeEvenementId;
    }

    public void setTypeEvenementId(Long typeEvenementId) {
        this.typeEvenementId = typeEvenementId;
    }

    public String getTypeEvenementLibelle() {
        return typeEvenementLibelle;
    }

    public void setTypeEvenementLibelle(String typeEvenementLibelle) {
        this.typeEvenementLibelle = typeEvenementLibelle;
    }
}