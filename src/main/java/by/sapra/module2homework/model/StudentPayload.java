package by.sapra.module2homework.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentPayload {
    private String firstName;
    private String lastName;
    private Integer age;
}
