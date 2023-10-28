package by.sapra.module2homework.events;

import by.sapra.module2homework.model.StudentRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class CreateEvent {
    private StudentRequest studentRequest;
    public CreateEvent(StudentRequest studentRequest) {
        this.studentRequest = studentRequest;
    }
}
