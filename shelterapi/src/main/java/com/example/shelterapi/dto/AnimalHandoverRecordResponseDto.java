package com.example.shelterapi.dto;

import java.time.LocalDateTime;

public class AnimalHandoverRecordResponseDto {
    private Long id;
    private Long applicationId;
    private LocalDateTime preparationDate;
    private LocalDateTime confirmationDate;
    private String status;

    public AnimalHandoverRecordResponseDto(Long id, Long applicationId, LocalDateTime preparationDate, LocalDateTime confirmationDate, String status) {
        this.id = id;
        this.applicationId = applicationId;
        this.preparationDate = preparationDate;
        this.confirmationDate = confirmationDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public Long getApplicationId() { return applicationId; }
    public LocalDateTime getPreparationDate() { return preparationDate; }
    public LocalDateTime getConfirmationDate() { return confirmationDate; }
    public String getStatus() { return status; }
}