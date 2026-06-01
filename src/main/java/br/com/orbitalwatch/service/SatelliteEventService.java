package br.com.orbitalwatch.service;

import br.com.orbitalwatch.dto.request.SatelliteEventRequestDTO;
import br.com.orbitalwatch.dto.response.SatelliteEventResponseDTO;
import br.com.orbitalwatch.entity.MonitoredRegion;
import br.com.orbitalwatch.entity.SatelliteEvent;
import br.com.orbitalwatch.entity.SatelliteMission;
import br.com.orbitalwatch.repository.MonitoredRegionRepository;
import br.com.orbitalwatch.repository.SatelliteEventRepository;
import br.com.orbitalwatch.repository.SatelliteMissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatelliteEventService {

    @Autowired
    private SatelliteEventRepository repository;

    @Autowired
    private SatelliteMissionRepository missionRepository;

    @Autowired
    private MonitoredRegionRepository regionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SatelliteEventResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SatelliteEventResponseDTO findById(Long id) {
        SatelliteEvent entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found: " + id));

        return toResponse(entity);
    }

    public SatelliteEventResponseDTO create(SatelliteEventRequestDTO dto) {

        SatelliteMission mission = missionRepository.findById(dto.getMissionId())
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        MonitoredRegion region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new RuntimeException("Region not found"));

        SatelliteEvent entity = new SatelliteEvent();

        entity.setEventType(dto.getEventType());
        entity.setSeverity(dto.getSeverity());
        entity.setEstimatedImpact(dto.getEstimatedImpact());
        entity.setObservationDate(dto.getObservationDate());
        entity.setStatus(dto.getStatus());
        entity.setDescription(dto.getDescription());
        entity.setSatelliteSource(dto.getSatelliteSource());

        entity.setMission(mission);
        entity.setRegion(region);

        SatelliteEvent savedEntity = repository.save(entity);

        return toResponse(savedEntity);
    }

    public SatelliteEventResponseDTO update(Long id, SatelliteEventRequestDTO dto) {

        SatelliteEvent entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        SatelliteMission mission = missionRepository.findById(dto.getMissionId())
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        MonitoredRegion region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new RuntimeException("Region not found"));

        entity.setEventType(dto.getEventType());
        entity.setSeverity(dto.getSeverity());
        entity.setEstimatedImpact(dto.getEstimatedImpact());
        entity.setObservationDate(dto.getObservationDate());
        entity.setStatus(dto.getStatus());
        entity.setDescription(dto.getDescription());
        entity.setSatelliteSource(dto.getSatelliteSource());

        entity.setMission(mission);
        entity.setRegion(region);

        SatelliteEvent updatedEntity = repository.save(entity);

        return toResponse(updatedEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SatelliteEventResponseDTO toResponse(SatelliteEvent entity) {

        SatelliteEventResponseDTO dto =
                modelMapper.map(entity, SatelliteEventResponseDTO.class);

        if (entity.getMission() != null) {
            dto.setMissionId(entity.getMission().getId());
            dto.setMissionName(entity.getMission().getMissionName());
        }

        if (entity.getRegion() != null) {
            dto.setRegionId(entity.getRegion().getId());
            dto.setRegionName(entity.getRegion().getRegionName());
        }

        return dto;
    }
}