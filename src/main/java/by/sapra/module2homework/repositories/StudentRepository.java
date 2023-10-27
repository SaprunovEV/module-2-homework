package by.sapra.module2homework.repositories;

import by.sapra.module2homework.model.entities.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final Map<UUID, StudentEntity> students;

    public StudentEntity save(StudentEntity student) {
        if (student.getId() == null){
            student.setId(UUID.randomUUID());
            students.put(student.getId(), student);
            return student;
        }
        return students.put(student.getId(), student);
    }

    public List<StudentEntity> clear() {
        List<StudentEntity> result = students.values().stream().toList();
        students.clear();
        return result;
    }

    public List<StudentEntity> findAll() {
        return students.values().stream().toList();
    }

    public Optional<StudentEntity> delete(UUID id) {
        StudentEntity remove = students.remove(id);
        return remove != null ? Optional.of(remove) : Optional.empty();
    }

    public Optional<StudentEntity> findById(UUID id) {
        StudentEntity entity = students.get(id);
        return entity != null ? Optional.of(entity) : Optional.empty();
    }
}
