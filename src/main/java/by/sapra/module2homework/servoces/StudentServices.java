package by.sapra.module2homework.servoces;

import by.sapra.module2homework.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentServices {
    List<Student> getAll();

    Student createNewStudent(Student student);

    Student deleteStudent(UUID eq);

    List<String> clearDatabase();
}
