package com.example.shelterapi.service;

import com.example.shelterapi.dto.AnimalHandoverRecordRequestDto;
import com.example.shelterapi.dto.AnimalHandoverRecordResponseDto;
import com.example.shelterapi.entity.AdoptionApplication;
import com.example.shelterapi.entity.AnimalHandoverRecord;
import com.example.shelterapi.exception.ResourceNotFoundException;
import com.example.shelterapi.repository.AdoptionApplicationRepository;
import com.example.shelterapi.repository.AnimalHandoverRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalHandoverRecordService {

    private final AnimalHandoverRecordRepository recordRepository;
    private final AdoptionApplicationRepository applicationRepository;

    public AnimalHandoverRecordService(AnimalHandoverRecordRepository recordRepository,
                                       AdoptionApplicationRepository applicationRepository) {
        this.recordRepository = recordRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<AnimalHandoverRecordResponseDto> getAll() {
        return recordRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public AnimalHandoverRecordResponseDto getById(Long id) {
        AnimalHandoverRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Акт не найден"));
        return mapToDto(record);
    }

    public AnimalHandoverRecordResponseDto create(AnimalHandoverRecordRequestDto dto) {
        AdoptionApplication app = applicationRepository.findById(dto.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Заявка с таким ID не найдена"));

        AnimalHandoverRecord record = new AnimalHandoverRecord();
        record.setApplication(app);
        record.setStatus(dto.getStatus());

        if ("CONFIRMED".equalsIgnoreCase(dto.getStatus())) {
            record.setConfirmationDate(LocalDateTime.now());
        }

        return mapToDto(recordRepository.save(record));
    }

    public AnimalHandoverRecordResponseDto update(Long id, AnimalHandoverRecordRequestDto dto) {
        AnimalHandoverRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Акт не найден"));
        
        record.setStatus(dto.getStatus());
        
        if ("CONFIRMED".equalsIgnoreCase(dto.getStatus()) && record.getConfirmationDate() == null) {
            record.setConfirmationDate(LocalDateTime.now());
        }

        return mapToDto(recordRepository.save(record));
    }

    public void delete(Long id) {
        if (!recordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Акт не найден");
        }
        recordRepository.deleteById(id);
    }

    private AnimalHandoverRecordResponseDto mapToDto(AnimalHandoverRecord record) {
        return new AnimalHandoverRecordResponseDto(
                record.getId(),
                record.getApplication().getId(),
                record.getPreparationDate(),
                record.getConfirmationDate(),
                record.getStatus()
        );
    }
}