package com.example.shelterapi.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "enclosures")
public class Enclosure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "enclosure_allowed_species", joinColumns = @JoinColumn(name = "enclosure_id"))
    @Column(name = "species")
    private List<String> allowedSpecies;

    @Column(nullable = false)
    private Integer capacity;

    public Enclosure() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public List<String> getAllowedSpecies() { return allowedSpecies; }
    public void setAllowedSpecies(List<String> allowedSpecies) { this.allowedSpecies = allowedSpecies; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}