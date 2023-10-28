package by.sapra.module2homework.events;

import by.sapra.module2homework.servoces.StudentServices;

public interface StudentEventPublisher {
    void deleteEventPublish(Object source, DeleteEvent any);

    void createStudentPublish(StudentServices eq, CreateEvent any);
}
