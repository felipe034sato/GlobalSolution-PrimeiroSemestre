package br.com.orbitalwatch.controller;

import br.com.orbitalwatch.dto.request.SatelliteMissionRequestDTO;
import br.com.orbitalwatch.dto.response.SatelliteMissionResponseDTO;
import br.com.orbitalwatch.service.SatelliteMissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v2/missions")
@Tag(name = "Satellite Missions", description = "Gerenciamento de missões espaciais")
public class SatelliteMissionController {

    @Autowired private SatelliteMissionService service;

    @GetMapping
    public ResponseEntity<List<SatelliteMissionResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SatelliteMissionResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SatelliteMissionResponseDTO> create(
            @Valid @RequestBody SatelliteMissionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SatelliteMissionResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody SatelliteMissionRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
