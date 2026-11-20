package com.example.shelterapi.dto;

import java.time.LocalDateTime;

public class AdoptionApplicationResponseDto {
    private Long id;
    private Long animalId;
    private Long applicantId;
    private String status;
    private LocalDateTime createdAt;

    public AdoptionApplicationResponseDto(Long id, Long animalId, Long applicantId, String status, LocalDateTime createdAt) {
        this.id = id;
        this.animalId = animalId;
        this.applicantId = applicantId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public Long getAnimalId() { return animalId; }
    public Long getApplicantId() { return applicantId; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}