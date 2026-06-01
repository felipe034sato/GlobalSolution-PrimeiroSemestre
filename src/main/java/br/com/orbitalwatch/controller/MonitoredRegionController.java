package br.com.orbitalwatch.controller;

import br.com.orbitalwatch.dto.request.MonitoredRegionRequestDTO;
import br.com.orbitalwatch.dto.response.MonitoredRegionResponseDTO;
import br.com.orbitalwatch.service.MonitoredRegionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v2/regions")
@Tag(name = "Monitored Regions", description = "Gerenciamento de regiões monitoradas")
public class MonitoredRegionController {

    @Autowired private MonitoredRegionService service;

    @GetMapping
    public ResponseEntity<List<MonitoredRegionResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonitoredRegionResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MonitoredRegionResponseDTO> create(
            @Valid @RequestBody MonitoredRegionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonitoredRegionResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody MonitoredRegionRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
