package by.sapra.module2homework.servoces.impl;

import by.sapra.module2homework.events.CreateEvent;
import by.sapra.module2homework.events.DeleteEvent;
import by.sapra.module2homework.events.pablishers.StudentEventPublisher;
import by.sapra.module2homework.model.StudentPayload;
import by.sapra.module2homework.model.StudentResponse;
import by.sapra.module2homework.model.entities.StudentEntity;
import by.sapra.module2homework.repositories.StudentRepository;
import by.sapra.module2homework.servoces.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class StudentServiceImpl implements StudentServices {
    private final StudentRepository repository;
    private final StudentEventPublisher publisher;
    @Override
    public List<StudentResponse> getAll() {
        List<StudentEntity> all = repository.findAll();
        return all.stream().map(s -> StudentResponse.builder()
                    .id(s.getId().toString()).age(s.getAge())
                    .firstName(s.getFirstName())
                    .lastName(s.getLastName())
                    .build())
                .toList();
    }

    @Override
    public StudentResponse createNewStudent(StudentPayload studentRequest) {
        StudentEntity entity = repository.save(StudentEntity.builder()
                .age(studentRequest.getAge())
                .lastName(studentRequest.getLastName())
                .firstName(studentRequest.getFirstName())
                .build());

        StudentResponse result = StudentResponse.builder()
                .age(entity.getAge())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .id(entity.getId().toString())
                .build();

        publisher.createStudentPublish(this, new CreateEvent(result));

        return result;
    }

    @Override
    public StudentResponse deleteStudent(String eq) {
        Optional<StudentEntity> entity = repository.delete(UUID.fromString(eq));
        if (entity.isPresent()) {
            StudentEntity student = entity.get();
            publisher.deleteEventPublish(this, new DeleteEvent(student.getId().toString()));
            return StudentResponse.builder()
                    .id(student.getId().toString())
                    .lastName(student.getLastName())
                    .firstName(student.getFirstName())
                    .build();
        }
        return StudentResponse.builder().id("").firstName("").lastName("").age(0).build();
    }

    @Override
    public List<String> clearDatabase() {
        return repository.clear().stream()
                .map(e -> e.getId().toString())
                .peek(id -> publisher.deleteEventPublish(this, new DeleteEvent(id)))
                .toList();
    }
}
