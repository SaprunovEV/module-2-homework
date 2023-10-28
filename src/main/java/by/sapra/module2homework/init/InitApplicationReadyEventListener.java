package by.sapra.module2homework.init;

import by.sapra.module2homework.model.entities.StudentEntity;
import by.sapra.module2homework.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InitApplicationReadyEventListener {
    private final StudentRepository repository;

    @EventListener(ContextRefreshedEvent.class)
    public void initStudentApplication() {
        List<StudentEntity> entities = getStudentList();
        entities.forEach(repository::save);
    }

    private List<StudentEntity> getStudentList() {
        List<StudentEntity> entities = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            entities.add(StudentEntity.builder()
                            .age(i)
                            .firstName("firstName" + i)
                            .lastName("lastName" + i)
                    .build());
        }
        return entities;
    }
}
