package com.example.shelterapi.service;

import com.example.shelterapi.dto.AdoptionApplicationRequestDto;
import com.example.shelterapi.dto.AdoptionApplicationResponseDto;
import com.example.shelterapi.entity.AdoptionApplication;
import com.example.shelterapi.entity.Animal;
import com.example.shelterapi.entity.User;
import com.example.shelterapi.exception.ResourceNotFoundException;
import com.example.shelterapi.repository.AdoptionApplicationRepository;
import com.example.shelterapi.repository.AnimalRepository;
import com.example.shelterapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptionApplicationService {

    private final AdoptionApplicationRepository applicationRepository;
    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;

    public AdoptionApplicationService(AdoptionApplicationRepository applicationRepository,
                                      AnimalRepository animalRepository,
                                      UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
    }

    public List<AdoptionApplicationResponseDto> getAll() {
        return applicationRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public AdoptionApplicationResponseDto getById(Long id) {
        AdoptionApplication app = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Заявка не найдена"));
        return mapToDto(app);
    }

    public AdoptionApplicationResponseDto create(AdoptionApplicationRequestDto dto) {
        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Животное с таким ID не найдено"));
        User user = userRepository.findById(dto.getApplicantId())
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с таким ID не найден"));

        AdoptionApplication app = new AdoptionApplication();
        app.setAnimal(animal);
        app.setApplicant(user);
        app.setStatus(dto.getStatus());

        return mapToDto(applicationRepository.save(app));
    }

    public AdoptionApplicationResponseDto update(Long id, AdoptionApplicationRequestDto dto) {
        AdoptionApplication app = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Заявка не найдена"));
        
        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Животное не найдено"));
        User user = userRepository.findById(dto.getApplicantId())
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        app.setAnimal(animal);
        app.setApplicant(user);
        app.setStatus(dto.getStatus());
        return mapToDto(applicationRepository.save(app));
    }

    public void delete(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Заявка не найдена");
        }
        applicationRepository.deleteById(id);
    }

    private AdoptionApplicationResponseDto mapToDto(AdoptionApplication app) {
        return new AdoptionApplicationResponseDto(
                app.getId(),
                app.getAnimal().getId(),
                app.getApplicant().getId(),
                app.getStatus(),
                app.getCreatedAt()
        );
    }
}