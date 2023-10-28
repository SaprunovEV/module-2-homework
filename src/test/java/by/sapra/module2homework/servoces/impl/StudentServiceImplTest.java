package by.sapra.module2homework.servoces.impl;

import by.sapra.module2homework.events.DeleteEvent;
import by.sapra.module2homework.events.StudentEventPublisher;
import by.sapra.module2homework.model.StudentRequest;
import by.sapra.module2homework.model.entities.StudentEntity;
import by.sapra.module2homework.repositories.StudentRepositoryConf;
import by.sapra.module2homework.servoces.StudentServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextHierarchy({
        @ContextConfiguration(classes = StudentRepositoryConf.class),
        @ContextConfiguration(classes = StudentServiceImplConf.class)
})
class StudentServiceImplTest {
    @Autowired
    private StudentServices services;

    @Autowired
    private Map<UUID, StudentEntity> studentMap;
    @Autowired
    private StudentEventPublisher studentEventPublisher;

    @Test
    void shouldReturnAllStudents() throws Exception {
        List<StudentEntity> entities = saveEntities();

        List<StudentRequest> actual = services.getAll();

        assertAll(() -> {
            assertEquals(entities.size(), actual.size());
            List<String> list = assertList(entities, actual);
            assertTrue(list.isEmpty());
        });
    }

    @Test
    void shouldReturnAllDeletedIds() throws Exception {
        List<String> expected = saveEntities().stream().map(entity -> entity.getId().toString()).toList();

        List<String> actual = services.clearDatabase();

        assertAll(() -> {
            assertEquals(expected.size(), actual.size());
            List<String> strings = actual.stream().filter(id -> !expected.contains(id)).toList();
            assertTrue(strings.isEmpty());
        });

        verify(studentEventPublisher, times(expected.size())).deleteEventPublish(eq(services), any(DeleteEvent.class));
    }

    @Test
    void shouldPublishIdDeletingStudent() throws Exception {
        String idForDelete = saveEntities().get(0).getId().toString();

        StudentRequest studentRequest = services.deleteStudent(idForDelete);
    }

    private List<String> assertList(List<StudentEntity> entities, List<StudentRequest> actual) {
        return actual.stream().map(StudentRequest::getId).filter(id -> !entities.stream().map(StudentEntity::getId).map(Objects::toString).toList().contains(id)).toList();
    }

    @AfterEach
    void tearDown() {
        studentMap.clear();
    }

    private List<StudentEntity> saveEntities() {
        List<StudentEntity> studentsList = List.of(
                getEntityWithId("first1", "last1", 23),
                getEntityWithId("first2", "last2", 23),
                getEntityWithId("first3", "last3", 23),
                getEntityWithId("first4", "last4", 23),
                getEntityWithId("first5", "last5", 23)
        );
        studentsList.forEach(s -> studentMap.put(s.getId(), s));
        return studentsList;
    }


    private StudentEntity getEntityWithId(String first1, String last1, int i) {
        StudentEntity entity = getEntity(first1, last1, i);
        entity.setId(UUID.randomUUID());
        return entity;
    }

    private static StudentEntity getEntity(String firstName, String lastName, int age) {
        return StudentEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();
    }
}