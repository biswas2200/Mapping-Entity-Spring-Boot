package com.mappings.manytomany.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "courses_jpa")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "c_title")
    private String title;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

}
