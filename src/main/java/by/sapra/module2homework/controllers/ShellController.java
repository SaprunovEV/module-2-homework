package by.sapra.module2homework.controllers;

import by.sapra.module2homework.model.StudentPayload;
import by.sapra.module2homework.model.StudentRequest;
import by.sapra.module2homework.servoces.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.UUID;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {

    private final StudentServices studentServices;

    @ShellMethod
    public String getAllStudents() {
        List<StudentRequest> allStudentRequests = studentServices.getAll();
        StringBuilder sb = new StringBuilder();
        for (StudentRequest studentRequest : allStudentRequests) sb.append(studentRequest.toString()).append("\n");
        return sb.toString();
    }

    @ShellMethod
    public void createNewStudent(String firstName, String lastName, int age) {
        StudentPayload studentPayload = StudentPayload.builder()
                .age(age)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        StudentRequest studentRequest = studentServices.createNewStudent(studentPayload);
    }

    @ShellMethod
    public void deleteStudent(UUID id) {
        studentServices.deleteStudent(id);
    }

    @ShellMethod
    public void clearAll() {
        studentServices.clearDatabase();
    }
}
