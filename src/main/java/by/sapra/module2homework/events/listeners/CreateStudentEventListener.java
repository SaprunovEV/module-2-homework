package by.sapra.module2homework.events.listeners;

import by.sapra.module2homework.events.ApplicationCreateStudentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class CreateStudentEventListener {

    @EventListener
    public void handleCreateStudentEvent(ApplicationCreateStudentEvent event) {
        System.out.println(MessageFormat.format("Student {0} have been created", event.getEvent().getStudentResponse()));
    }
}
