package com.example.shelterapi.service;

import com.example.shelterapi.dto.AnimalRequestDto;
import com.example.shelterapi.dto.AnimalResponseDto;
import com.example.shelterapi.entity.Animal;
import com.example.shelterapi.exception.ConflictException;
import com.example.shelterapi.exception.ResourceNotFoundException;
import com.example.shelterapi.repository.AdoptionApplicationRepository;
import com.example.shelterapi.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AdoptionApplicationRepository applicationRepository;

    public AnimalService(AnimalRepository animalRepository, AdoptionApplicationRepository applicationRepository) {
        this.animalRepository = animalRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<AnimalResponseDto> getAll() {
        return animalRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public AnimalResponseDto getById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Животное с ID " + id + " не найдено"));
        return mapToDto(animal);
    }

    public AnimalResponseDto create(AnimalRequestDto dto) {
        Animal animal = new Animal();
        animal.setName(dto.getName());
        animal.setSpecies(dto.getSpecies());
        animal.setAge(dto.getAge());
        animal.setPlacementStatus(dto.getPlacementStatus());

        Animal saved = animalRepository.save(animal);
        return mapToDto(saved);
    }

    public AnimalResponseDto update(Long id, AnimalRequestDto dto) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Животное с ID " + id + " не найдено"));

        animal.setName(dto.getName());
        animal.setSpecies(dto.getSpecies());
        animal.setAge(dto.getAge());
        animal.setPlacementStatus(dto.getPlacementStatus());

        Animal updated = animalRepository.save(animal);
        return mapToDto(updated);
    }

    public void delete(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Животное с ID " + id + " не найдено");
        }
        
        if (applicationRepository.existsByAnimalId(id)) {
            throw new ConflictException("Нельзя удалить животное, так как на него есть заявки на усыновление.");
        }
        
        animalRepository.deleteById(id);
    }

    private AnimalResponseDto mapToDto(Animal animal) {
        return new AnimalResponseDto(
                animal.getId(),
                animal.getName(),
                animal.getSpecies(),
                animal.getAge(),
                animal.getPlacementStatus()
        );
    }
}