package br.com.orbitalwatch.service;

import br.com.orbitalwatch.dto.request.SatelliteMissionRequestDTO;
import br.com.orbitalwatch.dto.response.SatelliteMissionResponseDTO;
import br.com.orbitalwatch.entity.SatelliteMission;
import br.com.orbitalwatch.repository.SatelliteMissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatelliteMissionService {

    @Autowired
    private SatelliteMissionRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public List<SatelliteMissionResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(e -> modelMapper.map(e, SatelliteMissionResponseDTO.class))
                .toList();
    }

    public SatelliteMissionResponseDTO findById(Long id) {
        SatelliteMission entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mission not found: " + id));
        return modelMapper.map(entity, SatelliteMissionResponseDTO.class);
    }

    public SatelliteMissionResponseDTO create(SatelliteMissionRequestDTO dto) {
        SatelliteMission entity = modelMapper.map(dto, SatelliteMission.class);
        return modelMapper.map(repository.save(entity), SatelliteMissionResponseDTO.class);
    }

    public SatelliteMissionResponseDTO update(Long id, SatelliteMissionRequestDTO dto) {
        SatelliteMission entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mission not found: " + id));
        modelMapper.map(dto, entity);
        entity.setId(id);
        return modelMapper.map(repository.save(entity), SatelliteMissionResponseDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
