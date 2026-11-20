package com.example.shelterapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AnimalHandoverRecordRequestDto {
    @NotNull(message = "ID заявки обязателен")
    private Long applicationId;

    @NotBlank(message = "Статус обязателен")
    private String status;

    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}