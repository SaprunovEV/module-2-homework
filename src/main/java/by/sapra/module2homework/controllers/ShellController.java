package by.sapra.module2homework.controllers;

import by.sapra.module2homework.model.Student;
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
        List<Student> allStudents = studentServices.getAll();
        StringBuilder sb = new StringBuilder();
        for (Student student : allStudents) sb.append(student.toString()).append("\n");
        return sb.toString();
    }
}
