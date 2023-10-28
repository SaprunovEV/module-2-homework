package by.sapra.module2homework.config;

import by.sapra.module2homework.init.InitApplicationReadyEventListener;
import by.sapra.module2homework.repositories.StudentRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty("app.init-mode.enabled")
public class InitConfig {
    @Bean
    public InitApplicationReadyEventListener initApplicationReadyEventListener(StudentRepository repo) {
        return new InitApplicationReadyEventListener(repo);
    }
}
