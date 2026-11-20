package com.example.shelterapi.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class EnclosureRequestDto {
    @NotNull(message = "Список видов не может быть null")
    private List<String> allowedSpecies;
    @NotNull(message = "Вместимость обязательна")
    @Min(value = 1, message = "Вместимость должна быть больше 0")
    private Integer capacity;

    public List<String> getAllowedSpecies() { return allowedSpecies; }
    public void setAllowedSpecies(List<String> allowedSpecies) { this.allowedSpecies = allowedSpecies; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}
