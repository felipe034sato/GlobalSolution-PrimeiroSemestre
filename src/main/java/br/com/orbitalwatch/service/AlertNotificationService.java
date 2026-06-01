package br.com.orbitalwatch.service;

import br.com.orbitalwatch.dto.request.AlertNotificationRequestDTO;
import br.com.orbitalwatch.dto.response.AlertNotificationResponseDTO;
import br.com.orbitalwatch.entity.AlertNotification;
import br.com.orbitalwatch.repository.AlertNotificationRepository;
import br.com.orbitalwatch.repository.SatelliteEventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertNotificationService {

    @Autowired
    private AlertNotificationRepository repository;
    @Autowired
    private SatelliteEventRepository eventRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<AlertNotificationResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(e -> toResponse(e)).toList();
    }

    public AlertNotificationResponseDTO findById(Long id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found: " + id)));
    }

    public AlertNotificationResponseDTO create(AlertNotificationRequestDTO dto) {
        AlertNotification entity = modelMapper.map(dto, AlertNotification.class);
        entity.setEvent(eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found: " + dto.getEventId())));
        return toResponse(repository.save(entity));
    }

    public AlertNotificationResponseDTO update(Long id, AlertNotificationRequestDTO dto) {
        AlertNotification entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found: " + id));
        modelMapper.map(dto, entity);
        entity.setId(id);
        entity.setEvent(eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found")));
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private AlertNotificationResponseDTO toResponse(AlertNotification e) {
        AlertNotificationResponseDTO dto = modelMapper.map(e, AlertNotificationResponseDTO.class);
        if (e.getEvent() != null) dto.setEventId(e.getEvent().getId());
        return dto;
    }
}
