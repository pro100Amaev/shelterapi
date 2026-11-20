package com.example.shelterapi.controller;

import com.example.shelterapi.dto.AnimalHandoverRecordRequestDto;
import com.example.shelterapi.dto.AnimalHandoverRecordResponseDto;
import com.example.shelterapi.service.AnimalHandoverRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal-handover-records")
public class AnimalHandoverRecordController {
    private final AnimalHandoverRecordService service;

    public AnimalHandoverRecordController(AnimalHandoverRecordService service) {
        this.service = service;
    }

    @GetMapping
    public List<AnimalHandoverRecordResponseDto> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public AnimalHandoverRecordResponseDto getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalHandoverRecordResponseDto create(@Valid @RequestBody AnimalHandoverRecordRequestDto dto) { return service.create(dto); }

    @PatchMapping("/{id}")
    public AnimalHandoverRecordResponseDto update(@PathVariable Long id, @Valid @RequestBody AnimalHandoverRecordRequestDto dto) { return service.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}