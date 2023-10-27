package by.sapra.module2homework.model.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class StudentEntity {
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
}
