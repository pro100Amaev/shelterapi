package com.example.shelterapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AdoptionApplicationRequestDto {
    @NotNull(message = "ID животного обязательно")
    private Long animalId;

    @NotNull(message = "ID пользователя обязательно")
    private Long applicantId;

    @NotBlank(message = "Статус обязателен")
    private String status;

    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public Long getApplicantId() { return applicantId; }
    public void setApplicantId(Long applicantId) { this.applicantId = applicantId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}