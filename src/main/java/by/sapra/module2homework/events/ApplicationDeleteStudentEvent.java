package by.sapra.module2homework.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ApplicationDeleteStudentEvent extends ApplicationEvent {

    private final DeleteEvent event;

    public ApplicationDeleteStudentEvent(Object source, DeleteEvent event) {
        super(source);
        this.event = event;
    }
}
