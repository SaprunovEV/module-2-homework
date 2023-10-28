package by.sapra.module2homework.servoces;

import by.sapra.module2homework.model.StudentPayload;
import by.sapra.module2homework.model.StudentRequest;

import java.util.List;
import java.util.UUID;

public interface StudentServices {
    List<StudentRequest> getAll();

    StudentRequest createNewStudent(StudentPayload studentRequest);

    StudentRequest deleteStudent(String eq);

    List<String> clearDatabase();
}
