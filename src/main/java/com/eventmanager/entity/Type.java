package com.eventmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long proprietaireId;

    @JsonIgnore
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Evenement> evenements = new ArrayList<>();


}
