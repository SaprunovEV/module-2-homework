package by.sapra.module2homework.repositories;

import by.sapra.module2homework.model.entities.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final Map<UUID, StudentEntity> students;

    public StudentEntity save(StudentEntity student) {
        student.setId(UUID.randomUUID());
        students.put(student.getId(), student);
        return student;
    }

    public void clear() {
        students.clear();
    }
}
