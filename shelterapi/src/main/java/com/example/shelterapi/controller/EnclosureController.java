package com.example.shelterapi.controller;

import com.example.shelterapi.dto.EnclosureRequestDto;
import com.example.shelterapi.dto.EnclosureResponseDto;
import com.example.shelterapi.service.EnclosureService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enclosures")
public class EnclosureController {
    private final EnclosureService enclosureService;

    public EnclosureController(EnclosureService enclosureService) {
        this.enclosureService = enclosureService;
    }

    @GetMapping
    public List<EnclosureResponseDto> getAll() { return enclosureService.getAll(); }

    @GetMapping("/{id}")
    public EnclosureResponseDto getById(@PathVariable Long id) { return enclosureService.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnclosureResponseDto create(@Valid @RequestBody EnclosureRequestDto dto) { return enclosureService.create(dto); }

    @PatchMapping("/{id}")
    public EnclosureResponseDto update(@PathVariable Long id, @Valid @RequestBody EnclosureRequestDto dto) { return enclosureService.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { enclosureService.delete(id); }
}
