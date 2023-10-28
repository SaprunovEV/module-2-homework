package by.sapra.module2homework.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ApplicationCreateStudentEvent extends ApplicationEvent {
    private final CreateEvent event;

    public ApplicationCreateStudentEvent(Object source, CreateEvent event) {
        super(source);
        this.event = event;
    }
}
