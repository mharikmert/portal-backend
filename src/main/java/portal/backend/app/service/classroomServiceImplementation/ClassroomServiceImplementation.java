package portal.backend.app.service.classroomServiceImplementation;

import portal.backend.app.model.Classroom;
import portal.backend.app.model.Student;
import portal.backend.app.model.Teacher;
import portal.backend.app.repository.ClassroomRepository;
import portal.backend.app.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ClassroomServiceImplementation implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomServiceImplementation(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public List<Classroom> getClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom createClassroom(Classroom classRef) {
        return classroomRepository.save(classRef);
    }

    @Override
    public Classroom updateClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom getClassroomByName(String name) {
        return classroomRepository.findClassroomByName(name);
    }

    @Override
    public Classroom getClassroomById(Long id) {
        return classroomRepository.findClassroomById(id);
    }

    @Override
    public void deleteClassroom(Classroom classRef) {
        classroomRepository.delete(classRef);
    }

    @Override
    public void deleteClassroomByName(String name) {
        classroomRepository.deleteClassroomByName(name);
    }
}
