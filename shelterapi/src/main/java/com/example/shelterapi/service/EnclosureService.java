package com.example.shelterapi.service;

import com.example.shelterapi.dto.EnclosureRequestDto;
import com.example.shelterapi.dto.EnclosureResponseDto;
import com.example.shelterapi.entity.Enclosure;
import com.example.shelterapi.exception.ResourceNotFoundException;
import com.example.shelterapi.repository.EnclosureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnclosureService {
    private final EnclosureRepository enclosureRepository;

    public EnclosureService(EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }

    public List<EnclosureResponseDto> getAll() {
        return enclosureRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public EnclosureResponseDto getById(Long id) {
        Enclosure e = enclosureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Вольер не найден"));
        return mapToDto(e);
    }

    public EnclosureResponseDto create(EnclosureRequestDto dto) {
        Enclosure e = new Enclosure();
        e.setAllowedSpecies(dto.getAllowedSpecies());
        e.setCapacity(dto.getCapacity());
        return mapToDto(enclosureRepository.save(e));
    }

    public EnclosureResponseDto update(Long id, EnclosureRequestDto dto) {
        Enclosure e = enclosureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Вольер не найден"));
        e.setAllowedSpecies(dto.getAllowedSpecies());
        e.setCapacity(dto.getCapacity());
        return mapToDto(enclosureRepository.save(e));
    }

    public void delete(Long id) {
        if (!enclosureRepository.existsById(id)) {
            throw new ResourceNotFoundException("Вольер не найден");
        }
        enclosureRepository.deleteById(id);
    }

    private EnclosureResponseDto mapToDto(Enclosure e) {
        return new EnclosureResponseDto(e.getId(), e.getAllowedSpecies(), e.getCapacity());
    }
}