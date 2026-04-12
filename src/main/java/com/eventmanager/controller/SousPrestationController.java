package com.eventmanager.controller;


import com.eventmanager.dto.SousPrestationDTO;
import com.eventmanager.entity.Prestation;
import com.eventmanager.entity.SousPrestation;
import com.eventmanager.repository.PrestationRepository;
import com.eventmanager.repository.SousPrestationRepository;
import com.eventmanager.service.MapperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sous-services")
@CrossOrigin("*")
public class SousPrestationController {

    private final SousPrestationRepository repo;
    private final PrestationRepository prestationRepo;
    private final MapperService mapper;

    public SousPrestationController(
            SousPrestationRepository repo,
            PrestationRepository prestationRepo,
            MapperService mapper
    ) {
        this.repo = repo;
        this.prestationRepo = prestationRepo;
        this.mapper = mapper;
    }

    // ---------------- CREATE SOUS SERVICE ----------------
    @PostMapping("/{prestationId}")
    public ResponseEntity<SousPrestationDTO> create(
            @PathVariable Long prestationId,
            @RequestBody SousPrestationDTO dto
    ) {
        Prestation p = prestationRepo.findById(prestationId)
                .orElseThrow(() -> new RuntimeException("Prestation not found"));

        SousPrestation sp = mapper.toSousEntity(dto);
        sp.setPrestation(p);

        SousPrestation saved = repo.save(sp);

        return ResponseEntity.status(201).body(mapper.toSousDto(saved));
    }

    // ---------------- GET ALL SOUS SERVICES ----------------
    @GetMapping
    public List<SousPrestationDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toSousDto)
                .toList();
    }

    // ---------------- GET BY PRESTATION ----------------
    @GetMapping("/prestation/{id}")
    public List<SousPrestationDTO> getByPrestation(@PathVariable Long id) {
        return repo.findByPrestationId(id)
                .stream()
                .map(mapper::toSousDto)
                .toList();
    }

    // ---------------- UPDATE SOUS SERVICE ----------------
    @PutMapping("/{id}")
    public ResponseEntity<SousPrestationDTO> update(
            @PathVariable Long id,
            @RequestBody SousPrestationDTO dto
    ) {
        SousPrestation sp = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("SousPrestation not found"));

        sp.setNom(dto.getNom());
        sp.setPrix(dto.getPrix());

        return ResponseEntity.ok(mapper.toSousDto(repo.save(sp)));
    }

    // ---------------- DELETE SOUS SERVICE ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}