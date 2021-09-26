package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Classroom;
import com.fikirtepe.app.model.Lecture;
import com.fikirtepe.app.model.Teacher;

import java.util.List;
import java.util.Set;

public interface LectureService {
    Lecture createLecture(Lecture lecture);
    List<Lecture> findAllLectures();
    Lecture findLectureById(long id);
    Lecture findLectureByName(String name);
    Set<Classroom> findClassroomsByLectureName(String name);
    Set<Teacher> findTeachersByLectureName(String name);
}
