package com.mappings.manytomany.service.impl;

import com.mappings.manytomany.dao.CoursesRepository;
import com.mappings.manytomany.dao.StudentRepository;
import com.mappings.manytomany.dto.CoursesDto;
import com.mappings.manytomany.dto.StudentDto;
import com.mappings.manytomany.entity.Courses;
import com.mappings.manytomany.entity.Student;
import com.mappings.manytomany.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    CoursesRepository coursesRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              CoursesRepository coursesRepository) {
        this.studentRepository = studentRepository;
        this.coursesRepository = coursesRepository;
    }

    private StudentDto mapToDtoStudent(Student student) {
        if (student == null)
            return null;
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setStudentName(student.getStudentName());

        return studentDto;
    }

    private CoursesDto mapToDtoCourse(Courses courses) {
        if (courses == null)
            return null;
        CoursesDto coursesDto = new CoursesDto();
        coursesDto.setId(courses.getId());
        coursesDto.setTitle(courses.getTitle());

        return coursesDto;
    }

    private Student mapToEntityStudent(StudentDto studentDto) {
        if (studentDto == null)
            return null;
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setStudentName(studentDto.getStudentName());

        return student;
    }

    private Courses mapToEntityCourses(CoursesDto coursesDto) {
        if (coursesDto == null)
            return null;
        Courses courses = new Courses();
        courses.setId(coursesDto.getId());
        courses.setTitle(coursesDto.getTitle());

        return courses;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::mapToDtoStudent)
                .collect(Collectors.toList());
    }

    @Override
    public List<CoursesDto> getAllCourses() {
        List<Courses> courses = coursesRepository.findAll();
        return courses.stream()
                .map(this::mapToDtoCourse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.map(this::mapToDtoStudent).orElse(null);
    }

    @Override
    public CoursesDto getCourseById(Long id) {
        Optional<Courses> optionalCourses = coursesRepository.findById(id);
        return optionalCourses.map(this::mapToDtoCourse).orElse(null);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = mapToEntityStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return mapToDtoStudent(savedStudent);
    }

    @Override
    public CoursesDto createCourses(CoursesDto coursesDto) {
        Courses courses = mapToEntityCourses(coursesDto);
        Courses savedCourses = coursesRepository.save(courses);
        return mapToDtoCourse(savedCourses);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null){
            existingStudent.setStudentName(studentDto.getStudentName());
            Student updatedStudent = studentRepository.save(existingStudent);
            return mapToDtoStudent(updatedStudent);
        }
        return null;
    }

    @Override
    public CoursesDto updateCourses(Long id, CoursesDto coursesDto) {
        Courses existingCourses = coursesRepository.findById(id).orElse(null);
        if (existingCourses != null){
            existingCourses.setTitle(coursesDto.getTitle());
            Courses updatedCourses = coursesRepository.save(existingCourses);
            return mapToDtoCourse(updatedCourses);
        }
        return null;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void deleteCourses(Long id) {
        coursesRepository.deleteById(id);
    }

    @Override
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        student.addCourse(course);
        studentRepository.save(student);
    }
}
