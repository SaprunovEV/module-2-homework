package by.sapra.module2homework.events;

public interface StudentEventPublisher {
    void deleteEventPublish(Object source, DeleteEvent any);

    void createStudentPublish(Object source, CreateEvent any);
}
