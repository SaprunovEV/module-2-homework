package by.sapra.module2homework.repositories;

import by.sapra.module2homework.model.entities.StudentEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.stream.Collectors;

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
    void shouldUpdateEntityIfIdIsPresent() throws Exception {
        List<StudentEntity> entities = saveEntities();

        UUID expectedId = entities.get(0).getId();
        StudentEntity expected = StudentEntity.builder()
                .id(expectedId)
                .firstName("newFn")
                .lastName("newLn")
                .age(34)
                .build();

        StudentEntity actual = repository.save(expected);

        assertAll(() -> {
            assertEquals(expectedId, actual.getId());
            assertStudent(expected, actual);
        });
    }

    @Test
    void shouldClearDatabase() throws Exception {
        List<StudentEntity> expected = saveEntities();

        List<StudentEntity> actual = repository.clear();

        assertAll(() -> {
            assertEquals(0, students.size());
            List<StudentEntity> emptyList = actual.stream().filter(s -> !expected.contains(s)).toList();
            assertTrue(emptyList.isEmpty());
        });
    }

    @Test
    void shouldClearDatabaseAndReturnEmptyListIfDatabaseWasEmpty() throws Exception {
        List<StudentEntity> actual = repository.clear();
        assertTrue(actual.isEmpty());
    }

    @Test
    void shouldFindOnlyExistingEntities() throws Exception {
        List<StudentEntity> entities = saveEntities();

        List<StudentEntity> actual = repository.findAll();

        assertAll(() -> {
            assertEquals(entities.size(), actual.size());
            List<StudentEntity> emptyList = actual.stream().filter(s -> !entities.contains(s)).toList();
            assertTrue(emptyList.isEmpty());
        });
    }

    @Test
    void shouldReturnEmptyList() throws Exception {
        List<StudentEntity> actual = repository.findAll();

        assertTrue(actual.isEmpty());
    }

    @Test
    void shouldDeleteStudentIfKeyIsPresent() throws Exception {
        List<StudentEntity> entities = saveEntities();

        Optional<StudentEntity> actual = repository.delete(entities.get(0).getId());

        assertAll(() -> {
            assertFalse(students.containsKey(entities.get(0).getId()));
            assertTrue(actual.isPresent());
        });
    }

    @Test
    void shouldReturnEmptyOptionalIfIdIsNotPresent() throws Exception {
        List<StudentEntity> entities = saveEntities();
        StudentEntity remove = students.remove(entities.get(0).getId());

        Optional<StudentEntity> actual = repository.delete(remove.getId());

        assertTrue(actual.isEmpty());
    }

    @Test
    void shouldFindEntityById() throws Exception {
        List<StudentEntity> entities = saveEntities();
        Optional<StudentEntity> optional = repository.findById(entities.get(0).getId());
        assertAll(() -> {
            assertTrue(optional.isPresent());
            assertEquals(entities.get(0), optional.get());
        });
    }

    @Test
    void shouldFindEmptyOptionalIfIdIsNotPresent() throws Exception {
        List<StudentEntity> entities = saveEntities();
        StudentEntity remove = students.remove(entities.get(0).getId());

        Optional<StudentEntity> actual = repository.findById(remove.getId());

        assertTrue(actual.isEmpty());
    }

    @AfterEach
    void tearDown() {
        students.clear();
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