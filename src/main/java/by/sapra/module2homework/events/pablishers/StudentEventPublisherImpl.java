package by.sapra.module2homework.events.pablishers;

import by.sapra.module2homework.events.ApplicationCreateStudentEvent;
import by.sapra.module2homework.events.ApplicationDeleteStudentEvent;
import by.sapra.module2homework.events.CreateEvent;
import by.sapra.module2homework.events.DeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class StudentEventPublisherImpl implements StudentEventPublisher {
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
