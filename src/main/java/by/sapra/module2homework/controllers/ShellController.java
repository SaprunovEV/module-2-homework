package by.sapra.module2homework.controllers;

import by.sapra.module2homework.model.Student;
import by.sapra.module2homework.servoces.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.text.MessageFormat;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {

    private final StudentServices studentServices;

    @ShellMethod
    public String getAllStudents() {
        List<Student> allStudents = studentServices.getAll();
        StringBuilder sb = new StringBuilder();
        for (Student student : allStudents) sb.append(student.toString()).append("\n");
        return sb.toString();
    }

    @ShellMethod
    public String createNewStudent(String firstName, String lastName, int age) {
        Student newStudent = Student.builder()
                .age(age)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        Student student = studentServices.createNewStudent(newStudent);
        return MessageFormat.format("New student with id {0} have to created", student.getId());
    }
}
