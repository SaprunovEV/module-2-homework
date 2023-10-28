package by.sapra.module2homework.events.listeners;

import by.sapra.module2homework.events.ApplicationDeleteStudentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class DeleteStudentEventListener {
    @EventListener
    public void handleDeleteStudentEvent(ApplicationDeleteStudentEvent event) {
        System.out.println(MessageFormat.format("Student with id {0} has been deleted.", event.getEvent().getId()));
    }
}
