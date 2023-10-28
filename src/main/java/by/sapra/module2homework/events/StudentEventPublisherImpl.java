package by.sapra.module2homework.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentEventPublisherImpl implements StudentEventPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void deleteEventPublish(Object source, DeleteEvent event) {
        publisher.publishEvent(new ApplicationDeleteStudentEvent(source, event));
    }

    @Override
    public void createStudentPublish(Object source, CreateEvent event) {
        publisher.publishEvent(new ApplicationCreateStudentEvent(source, event));
    }
}
