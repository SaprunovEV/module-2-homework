package by.sapra.module2homework.repositories;

import by.sapra.module2homework.model.entities.StudentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
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

    @Test
    void shouldClearDatabase() throws Exception {
        saveEntities();

        repository.clear();

        assertEquals(0, students.size());
    }

    private List<StudentEntity> saveEntities() {
        List<StudentEntity> studentsList = List.of(
                getEntityWithId("first1", "last1", 23),
                getEntityWithId("first2", "last2", 23),
                getEntityWithId("first3", "last3", 23),
                getEntityWithId("first4", "last4", 23),
                getEntityWithId("first5", "last5", 23)
        );
        studentsList.forEach(s -> students.put(s.getId(), s));
        return studentsList;
    }


    private StudentEntity getEntityWithId(String first1, String last1, int i) {
        StudentEntity entity = getEntity(first1, last1, i);
        entity.setId(UUID.randomUUID());
        return entity;
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