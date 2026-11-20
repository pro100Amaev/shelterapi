package com.example.shelterapi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "animal_handover_records")
public class AnimalHandoverRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "application_id", nullable = false)
    private AdoptionApplication application;

    @Column(nullable = false, updatable = false)
    private LocalDateTime preparationDate;

    private LocalDateTime confirmationDate;

    @Column(nullable = false)
    private String status;

    @PrePersist
    protected void onCreate() {
        this.preparationDate = LocalDateTime.now();
    }

    public AnimalHandoverRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public AdoptionApplication getApplication() { return application; }
    public void setApplication(AdoptionApplication application) { this.application = application; }
    public LocalDateTime getPreparationDate() { return preparationDate; }
    public LocalDateTime getConfirmationDate() { return confirmationDate; }
    public void setConfirmationDate(LocalDateTime confirmationDate) { this.confirmationDate = confirmationDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}