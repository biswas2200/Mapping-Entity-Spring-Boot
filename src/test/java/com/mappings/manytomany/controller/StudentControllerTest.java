package com.mappings.manytomany.controller;

//integration testing.

import com.mappings.manytomany.dto.StudentDto;
import com.mappings.manytomany.service.StudentService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void getAllStudents_Test() throws Exception {
        List<Long> coursesDtoList1 = Arrays.asList(101L, 102L);
        List<Long> coursesDtoList2 = Arrays.asList(103L, 104L);
        StudentDto student1 = new StudentDto();
        student1.setId(1L);
        student1.setStudentName("Manik");
        student1.setCourseId(coursesDtoList1);
        StudentDto student2 = new StudentDto();
        student2.setId(2L);
        student2.setStudentName("Saho");
        student2.setCourseId(coursesDtoList2);
        List<StudentDto> studentDtoList = Arrays.asList(student1, student2);
        when(studentService.getAllStudents()).thenReturn(studentDtoList);

        mockMvc.perform(get("/api/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(student1.getId().longValue()))
                .andExpect(jsonPath("$[0].studentName").value(student1.getStudentName()))
                .andExpect(jsonPath("$[0].courseId").isArray())
                .andExpect(jsonPath("$[0].courseId[0]").value(coursesDtoList1.get(0).longValue()))
                .andExpect(jsonPath("$[0].courseId[1]").value(coursesDtoList1.get(1).longValue()))
                .andExpect(jsonPath("$[1].studentName").value(student2.getStudentName()))
                .andExpect(jsonPath("$[1].courseId").isArray())
                .andExpect(jsonPath("$[1].courseId[0]").value(coursesDtoList2.get(0).longValue()))
                .andExpect(jsonPath("$[1].courseId[1]").value(coursesDtoList2.get(1).longValue()));


    }

    @Test
    void getStudentById() throws Exception {
        List<Long> courseDto = Arrays.asList(101L, 102L);
        StudentDto studentDto = new StudentDto(1L, "Manik", courseDto);
        when(studentService.getStudentById(1L)).thenReturn(studentDto);

        mockMvc.perform(get("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(studentDto.getId().longValue()))
                .andExpect(jsonPath("$.studentName").value(studentDto.getStudentName()))
                .andExpect(jsonPath("$.courseId[0]").value(studentDto.getCourseId().get(0)))
                .andExpect(jsonPath("$.courseId[1]").value(studentDto.getCourseId().get(1)));
    }

    @Test
    void createStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void getAllCourses() {
    }

    @Test
    void getCourseById() {
    }

    @Test
    void createCourse() {
    }

    @Test
    void updateCourse() {
    }

    @Test
    void deleteCourse() {
    }

    @Test
    void enrollStudentInCourse() {
    }
}