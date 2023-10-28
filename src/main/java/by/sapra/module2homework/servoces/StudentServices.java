package by.sapra.module2homework.servoces;

import by.sapra.module2homework.model.StudentPayload;
import by.sapra.module2homework.model.StudentResponse;

import java.util.List;

public interface StudentServices {
    List<StudentResponse> getAll();

    StudentResponse createNewStudent(StudentPayload studentRequest);

    StudentResponse deleteStudent(String eq);

    List<String> clearDatabase();
}
