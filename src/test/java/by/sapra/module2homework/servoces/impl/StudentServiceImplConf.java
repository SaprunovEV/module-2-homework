package by.sapra.module2homework.servoces.impl;

import by.sapra.module2homework.events.pablishers.StudentEventPublisher;
import by.sapra.module2homework.repositories.StudentRepository;
import by.sapra.module2homework.servoces.StudentServices;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@MockBean(StudentEventPublisher.class)
public class StudentServiceImplConf {
    @Bean
    public StudentServices studentServices(StudentRepository repository, StudentEventPublisher publisher) {
        return new StudentServiceImpl(repository, publisher);
    }
}
