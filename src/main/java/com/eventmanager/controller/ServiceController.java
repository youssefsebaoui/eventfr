package com.eventmanager.controller;

import com.eventmanager.dto.PrestationDTO;
import com.eventmanager.service.PrestationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*")
public class ServiceController {

    private final PrestationService s;

    public ServiceController(PrestationService s) {
        this.s = s;
    }

    // ---------------- GET USER SERVICES ----------------
    @GetMapping
    public List<PrestationDTO> getAll(Authentication a) {
        return s.findAll(a.getName());
    }

    // ---------------- GET PUBLIC SERVICES ----------------
    @GetMapping("/all")
    public List<PrestationDTO> getAllPublic() {
        return s.findAllPublic();
    }

    // ---------------- CREATE SERVICE ----------------
    @PostMapping
    public ResponseEntity<PrestationDTO> create(
            @RequestBody PrestationDTO d,
            Authentication a
    ) {
        PrestationDTO dto = s.create(d, a.getName());
        return ResponseEntity.status(201).body(dto);
    }

    // ---------------- UPDATE SERVICE ----------------
    @PutMapping("/{id}")
    public ResponseEntity<PrestationDTO> update(
            @PathVariable Long id,
            @RequestBody PrestationDTO d
    ) {
        PrestationDTO dto = s.update(id, d);
        return ResponseEntity.ok(dto);
    }

    // ---------------- DELETE SERVICE ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        s.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------- GET BY ID (OPTIONAL MAIS RECOMMANDÉ) ----------------
    @GetMapping("/{id}")
    public ResponseEntity<PrestationDTO> getById(@PathVariable Long id) {
        PrestationDTO dto = s.findAllPublic()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        return dto != null
                ? ResponseEntity.ok(dto)
                : ResponseEntity.notFound().build();
    }
}