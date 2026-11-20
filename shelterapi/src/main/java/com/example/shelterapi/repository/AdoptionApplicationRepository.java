package com.example.shelterapi.repository;

import com.example.shelterapi.entity.AdoptionApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Long> {
    
    boolean existsByAnimalId(Long animalId);

    boolean existsByApplicantId(Long userId);
}