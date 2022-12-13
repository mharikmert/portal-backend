package portal.backend.app.repository;

import portal.backend.app.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Classroom findClassroomByName(String className);

    Classroom findClassroomById(Long id);

    void deleteClassroomByName(String name);

    List<Classroom> findAll();
}
