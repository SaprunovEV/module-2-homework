package by.sapra.module2homework.events;

import by.sapra.module2homework.model.StudentResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class CreateEvent {
    private StudentResponse studentResponse;
    public CreateEvent(StudentResponse studentResponse) {
        this.studentResponse = studentResponse;
    }
}
