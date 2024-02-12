package com.mappings.manytomany.controller;

import com.mappings.manytomany.dto.CoursesDto;
import com.mappings.manytomany.dto.StudentDto;
import com.mappings.manytomany.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto student = studentService.getStudentById(id);
        return student != null ? new ResponseEntity<>(student, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto createdStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long
                                                            id, @RequestBody StudentDto studentDto) {
        StudentDto updatedStudent = studentService.updateStudent(id, studentDto);
        return updatedStudent != null ? new ResponseEntity<>(updatedStudent, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CoursesDto>> getAllCourses() {
        List<CoursesDto> courses = studentService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CoursesDto> getCourseById(@PathVariable Long id) {
        CoursesDto course = studentService.getCourseById(id);
        return course != null ? new ResponseEntity<>(course, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/courses")
    public ResponseEntity<CoursesDto> createCourse(@RequestBody CoursesDto coursesDto) {
        CoursesDto createdCourse = studentService.createCourses(coursesDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CoursesDto> updateCourse(@PathVariable Long
                                                           id, @RequestBody CoursesDto coursesDto) {
        CoursesDto updatedCourse = studentService.updateCourses(id, coursesDto);
        return updatedCourse != null ? new ResponseEntity<>(updatedCourse, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        studentService.deleteCourses(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Void> enrollStudentInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.enrollStudentInCourse(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
