package com.mappings.manytomany.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "student_jpa")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "s_name")
    private String studentName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Courses> courses;

    public void addCourse(Courses course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.getStudents().add(this);
    }

}
