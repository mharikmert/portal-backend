package com.fikirtepe.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Lecture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lectureCode;

    @ManyToMany//(mappedBy = "lectures", fetch = FetchType.LAZY)
    private Set<Student> students =  new HashSet<>();

    @ManyToMany//(mappedBy = "lectures", fetch = FetchType.LAZY)
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany
//    @JoinTable(name = "lectures_classrooms",
//            joinColumns = @JoinColumn(name = "lecture_id"),
//            inverseJoinColumns = @JoinColumn(name = "classroom_id"))
    private Set<Classroom> classrooms;

}
