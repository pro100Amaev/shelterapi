package com.example.shelterapi.dto;

public class AnimalResponseDto {
    private Long id;
    private String name;
    private String species;
    private Integer age;
    private String placementStatus;

    public AnimalResponseDto(Long id, String name, String species, Integer age, String placementStatus) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.placementStatus = placementStatus;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public Integer getAge() { return age; }
    public String getPlacementStatus() { return placementStatus; }
}