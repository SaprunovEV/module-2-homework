package by.sapra.module2homework.controllers;

import by.sapra.module2homework.model.StudentPayload;
import by.sapra.module2homework.model.StudentResponse;
import by.sapra.module2homework.servoces.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {

    private final StudentServices studentServices;

    @ShellMethod
    public String getAllStudents() {
        List<StudentResponse> allStudentResponses = studentServices.getAll();
        StringBuilder sb = new StringBuilder();
        for (StudentResponse studentResponse : allStudentResponses) sb.append(studentResponse.toString()).append("\n");
        return sb.toString();
    }

    @ShellMethod
    public void createNewStudent(String firstName, String lastName, int age) {
        StudentPayload studentPayload = StudentPayload.builder()
                .age(age)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        StudentResponse studentResponse = studentServices.createNewStudent(studentPayload);
    }

    @ShellMethod
    public void deleteStudent(String id) {
        studentServices.deleteStudent(id);
    }

    @ShellMethod
    public void clearAll() {
        studentServices.clearDatabase();
    }
}
