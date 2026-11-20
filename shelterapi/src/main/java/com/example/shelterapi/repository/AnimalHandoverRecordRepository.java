package com.example.shelterapi.repository;

import com.example.shelterapi.entity.AnimalHandoverRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalHandoverRecordRepository extends JpaRepository<AnimalHandoverRecord, Long> {
    
    boolean existsByApplicationId(Long applicationId);
}