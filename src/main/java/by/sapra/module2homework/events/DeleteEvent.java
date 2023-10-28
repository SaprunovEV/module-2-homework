package by.sapra.module2homework.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class DeleteEvent {
    private String id;
    public DeleteEvent(String id) {
        this.id = id;
    }
}
