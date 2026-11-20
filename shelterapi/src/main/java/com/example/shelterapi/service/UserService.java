package com.example.shelterapi.service;

import com.example.shelterapi.dto.UserRequestDto;
import com.example.shelterapi.dto.UserResponseDto;
import com.example.shelterapi.entity.User;
import com.example.shelterapi.exception.ConflictException;
import com.example.shelterapi.exception.ResourceNotFoundException;
import com.example.shelterapi.repository.AdoptionApplicationRepository;
import com.example.shelterapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder; // Импорт энкодера
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AdoptionApplicationRepository applicationRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, 
                       AdoptionApplicationRepository applicationRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public UserResponseDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        return mapToDto(user);
    }

    public UserResponseDto create(UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());
        
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        
        return mapToDto(userRepository.save(user));
    }

    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        user.setUsername(dto.getUsername());
        
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        
        user.setRole(dto.getRole());
        return mapToDto(userRepository.save(user));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Пользователь не найден");
        }
        if (applicationRepository.existsByApplicantId(id)) {
            throw new ConflictException("Нельзя удалить пользователя, у него есть заявки на усыновление.");
        }
        userRepository.deleteById(id);
    }

    private UserResponseDto mapToDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getRole());
    }
}