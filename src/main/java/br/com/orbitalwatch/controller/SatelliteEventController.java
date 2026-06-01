package br.com.orbitalwatch.controller;

import br.com.orbitalwatch.dto.request.SatelliteEventRequestDTO;
import br.com.orbitalwatch.dto.response.SatelliteEventResponseDTO;
import br.com.orbitalwatch.service.SatelliteEventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v2/events")
@Tag(name = "Satellite Events", description = "Gerenciamento de eventos satelitais")
public class SatelliteEventController {

    @Autowired private SatelliteEventService service;

    @GetMapping
    public ResponseEntity<List<SatelliteEventResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SatelliteEventResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SatelliteEventResponseDTO> create(
            @Valid @RequestBody SatelliteEventRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SatelliteEventResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody SatelliteEventRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
