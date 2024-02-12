package com.mappings.manytomany.service;

import com.mappings.manytomany.dto.CoursesDto;
import com.mappings.manytomany.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<StudentDto> getAllStudents();

    List<CoursesDto> getAllCourses();

    StudentDto getStudentById(Long id);

    CoursesDto getCourseById(Long id);


    StudentDto createStudent(StudentDto studentDto);

    CoursesDto createCourses(CoursesDto coursesDto);

    StudentDto updateStudent(Long id, StudentDto studentDto);

    CoursesDto updateCourses(Long id, CoursesDto coursesDto);

    void deleteStudent(Long id);

    void deleteCourses(Long id);

    void enrollStudentInCourse(Long studentId, Long courseId);
}
