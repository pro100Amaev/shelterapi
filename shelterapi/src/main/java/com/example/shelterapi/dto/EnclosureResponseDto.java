package com.example.shelterapi.dto;
import java.util.List;

public class EnclosureResponseDto {
    private Long id;
    private List<String> allowedSpecies;
    private Integer capacity;

    public EnclosureResponseDto(Long id, List<String> allowedSpecies, Integer capacity) {
        this.id = id; this.allowedSpecies = allowedSpecies; this.capacity = capacity;
    }
    public Long getId() { return id; }
    public List<String> getAllowedSpecies() { return allowedSpecies; }
    public Integer getCapacity() { return capacity; }
}