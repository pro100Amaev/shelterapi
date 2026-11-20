package com.example.shelterapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AnimalRequestDto {

    @NotBlank(message = "Кличка не может быть пустой")
    private String name;

    @NotBlank(message = "Вид животного обязателен")
    private String species;

    @NotNull(message = "Возраст обязателен")
    @Min(value = 0, message = "Возраст не может быть отрицательным")
    private Integer age;

    @NotBlank(message = "Статус размещения обязателен")
    private String placementStatus;

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getPlacementStatus() { return placementStatus; }
    public void setPlacementStatus(String placementStatus) { this.placementStatus = placementStatus; }
}