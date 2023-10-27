package by.sapra.module2homework.controllers;

import by.sapra.module2homework.model.Student;
import by.sapra.module2homework.servoces.StudentServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShellControllerTest {

    @InjectMocks
    private ShellController shellController;
    @Mock
    private StudentServices studentServices;

    @Test
    void shouldStringOfAllStudents() throws Exception {

        String actual = shellController.getAllStudents();

        assertNotNull(actual);
    }

    @Test
    void shouldCorrectCollectTheString() throws Exception {
        List<Student> students = List.of(
                Student.builder().age(12).firstName("one").lastName("lone").build(),
                Student.builder().age(12).firstName("two").lastName("ltwo").build(),
                Student.builder().age(12).firstName("three").lastName("lthree").build()
        );
        when(studentServices.getAll()).thenReturn(students);

        String actual = shellController.getAllStudents();

        assertStudentsString(students, actual);
    }

    @Test
    void shouldCallStudentServiceAndPrepareAResultString() throws Exception {
        String firstName = "testFirstName";
        String lastName = "testLastName";
        int age = 23;

        Student student = Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();
        Student resultStudent = Student.builder().id(UUID.randomUUID()).build();
        when(studentServices.createNewStudent(student)).thenReturn(resultStudent);

        shellController.createNewStudent(firstName, lastName, age);

        verify(studentServices, times(1)).createNewStudent(eq(student));
    }

    @Test
    void shouldCallStudentServiceForDeletingStudent() throws Exception {
        UUID id = UUID.randomUUID();

        shellController.deleteStudent(id);

        verify(studentServices, times(1)).deleteStudent(eq(id));
    }

    private void assertStudentsString(List<Student> students, String actual) {
        for (Student student : students) {
            Stream<String> lines = actual.trim().lines();
            String expectedFirstName = "firstName='" + student.getFirstName() + "'";
            String expectedLastName = "lastName='" + student.getLastName() + "'";
            String expectedAge = "age=" + student.getAge();
            Optional<String> first = lines
                    .filter(getStringPredicate(expectedFirstName, expectedLastName, expectedAge)
                    ).findFirst();

            assertTrue(first.isPresent(), MessageFormat.format("student: {0}, {1}, {2}", expectedFirstName, expectedLastName, expectedAge));
        }
    }

    private Predicate<String> getStringPredicate(String expectedFirstName, String expectedLastName, String expectedAge) {
        return l ->
        {
            boolean containsA = l.contains(expectedAge);
            boolean containsF = l.contains(expectedFirstName);
            boolean containsL = l.contains(expectedLastName);
            return containsF &&
                    containsL &&
                    containsA;
        };
    }
}