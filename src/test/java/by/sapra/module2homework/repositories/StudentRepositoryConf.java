package by.sapra.module2homework.repositories;

import by.sapra.module2homework.model.entities.StudentEntity;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@TestConfiguration
public class StudentRepositoryConf {
    @Bean
    public Map<UUID, StudentEntity> students() {
        return new HashMap<>();
    }

    @Bean
    public StudentRepository studentRepository() {
        return new StudentRepository(students());
    }
}
