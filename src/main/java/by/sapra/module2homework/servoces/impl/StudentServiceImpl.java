package by.sapra.module2homework.servoces.impl;

import by.sapra.module2homework.model.StudentPayload;
import by.sapra.module2homework.model.StudentRequest;
import by.sapra.module2homework.servoces.StudentServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentServices {
    @Override
    public List<StudentRequest> getAll() {
        return null;
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
