package by.sapra.module2homework.events.pablishers;

import by.sapra.module2homework.events.CreateEvent;
import by.sapra.module2homework.events.DeleteEvent;

public interface StudentEventPublisher {
    void deleteEventPublish(Object source, DeleteEvent any);

    void createStudentPublish(Object source, CreateEvent any);
}
