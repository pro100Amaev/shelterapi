package com.example.shelterapi.controller;

import com.example.shelterapi.dto.AnimalRequestDto;
import com.example.shelterapi.dto.AnimalResponseDto;
import com.example.shelterapi.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<AnimalResponseDto> getAll() {
        return animalService.getAll();
    }

    @GetMapping("/{id}")
    public AnimalResponseDto getById(@PathVariable Long id) {
        return animalService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponseDto create(@Valid @RequestBody AnimalRequestDto dto) {
        return animalService.create(dto);
    }

    @PatchMapping("/{id}")
    public AnimalResponseDto update(@PathVariable Long id, @Valid @RequestBody AnimalRequestDto dto) {
        return animalService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        animalService.delete(id);
    }
}
