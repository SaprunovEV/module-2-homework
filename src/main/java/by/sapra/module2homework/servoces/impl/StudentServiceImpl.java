package by.sapra.module2homework.servoces.impl;

import by.sapra.module2homework.model.StudentPayload;
import by.sapra.module2homework.model.StudentRequest;
import by.sapra.module2homework.model.entities.StudentEntity;
import by.sapra.module2homework.repositories.StudentRepository;
import by.sapra.module2homework.servoces.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentServices {
    private final StudentRepository repository;
    @Override
    public List<StudentRequest> getAll() {
        List<StudentEntity> all = repository.findAll();
        return all.stream().map(s -> StudentRequest.builder()
                    .id(s.getId().toString()).age(s.getAge())
                    .firstName(s.getFirstName())
                    .lastName(s.getLastName())
                    .build())
                .toList();
    }

    @Override
    public StudentRequest createNewStudent(StudentPayload studentRequest) {
        return null;
    }

    @Override
    public StudentRequest deleteStudent(UUID eq) {
        return null;
    }

    @Override
    public List<String> clearDatabase() {
        return null;
    }
}
