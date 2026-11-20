package com.example.shelterapi.controller;

import com.example.shelterapi.dto.AdoptionApplicationRequestDto;
import com.example.shelterapi.dto.AdoptionApplicationResponseDto;
import com.example.shelterapi.service.AdoptionApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoption-applications")
public class AdoptionApplicationController {
    private final AdoptionApplicationService service;

    public AdoptionApplicationController(AdoptionApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public List<AdoptionApplicationResponseDto> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public AdoptionApplicationResponseDto getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdoptionApplicationResponseDto create(@Valid @RequestBody AdoptionApplicationRequestDto dto) { return service.create(dto); }

    @PatchMapping("/{id}")
    public AdoptionApplicationResponseDto update(@PathVariable Long id, @Valid @RequestBody AdoptionApplicationRequestDto dto) { return service.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}