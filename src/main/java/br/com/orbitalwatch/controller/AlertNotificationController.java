package br.com.orbitalwatch.controller;

import br.com.orbitalwatch.dto.request.AlertNotificationRequestDTO;
import br.com.orbitalwatch.dto.response.AlertNotificationResponseDTO;
import br.com.orbitalwatch.service.AlertNotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v2/alerts")
@Tag(name = "Alert Notifications", description = "Gerenciamento de notificações de alerta")
public class AlertNotificationController {

    @Autowired private AlertNotificationService service;

    @GetMapping
    public ResponseEntity<List<AlertNotificationResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertNotificationResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AlertNotificationResponseDTO> create(
            @Valid @RequestBody AlertNotificationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertNotificationResponseDTO> update(
            @PathVariable Long id, @Valid @RequestBody AlertNotificationRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
