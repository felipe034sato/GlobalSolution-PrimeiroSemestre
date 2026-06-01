package br.com.orbitalwatch.service;

import br.com.orbitalwatch.dto.request.MonitoredRegionRequestDTO;
import br.com.orbitalwatch.dto.response.MonitoredRegionResponseDTO;
import br.com.orbitalwatch.entity.MonitoredRegion;
import br.com.orbitalwatch.repository.MonitoredRegionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitoredRegionService {

    @Autowired
    private MonitoredRegionRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public List<MonitoredRegionResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(e -> modelMapper.map(e, MonitoredRegionResponseDTO.class)).toList();
    }

    public MonitoredRegionResponseDTO findById(Long id) {
        MonitoredRegion entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region not found: " + id));
        return modelMapper.map(entity, MonitoredRegionResponseDTO.class);
    }

    public MonitoredRegionResponseDTO create(MonitoredRegionRequestDTO dto) {
        MonitoredRegion entity = modelMapper.map(dto, MonitoredRegion.class);
        return modelMapper.map(repository.save(entity), MonitoredRegionResponseDTO.class);
    }

    public MonitoredRegionResponseDTO update(Long id, MonitoredRegionRequestDTO dto) {
        MonitoredRegion entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region not found: " + id));
        modelMapper.map(dto, entity);
        entity.setId(id);
        return modelMapper.map(repository.save(entity), MonitoredRegionResponseDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
