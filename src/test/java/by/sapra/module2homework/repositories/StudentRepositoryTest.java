package by.sapra.module2homework.repositories;

import by.sapra.module2homework.model.entities.StudentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StudentRepositoryConf.class)
class StudentRepositoryTest {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private Map<UUID, StudentEntity> students;

    @Test
    void shouldSaveStudentWithUniqueKey() throws Exception {
        StudentEntity student = getEntity("firstName", "lastName", 23);

        StudentEntity actual = repository.save(student);

        assertStudent(student, actual);
    }

    private void assertStudent(StudentEntity student, StudentEntity actual) {
        assertAll(() -> {
            assertTrue(students.containsKey(actual.getId()));
            StudentEntity savedStudent = students.get(actual.getId());
            assertNotNull(savedStudent);
            assertEquals(savedStudent.getFirstName(), student.getFirstName());
            assertEquals(savedStudent.getLastName(), student.getLastName());
            assertEquals(savedStudent.getAge(), student.getAge());
        });
    }

    private static StudentEntity getEntity(String firstName, String lastName, int age) {
        return StudentEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();
    }
}