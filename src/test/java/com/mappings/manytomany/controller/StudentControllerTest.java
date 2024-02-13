package com.mappings.manytomany.controller;

//integration testing.

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mappings.manytomany.dto.CoursesDto;
import com.mappings.manytomany.dto.StudentDto;
import com.mappings.manytomany.service.StudentService;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

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
    void createStudent() throws Exception {
        List<Long>courseDTO = Arrays.asList(101L, 102L);
        StudentDto Student1 = new StudentDto(1L, "ManikCreated", courseDTO);

        when(studentService.createStudent(Mockito.any())).thenReturn(Student1);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Student1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(Student1.getId().longValue()))
                .andExpect(jsonPath("$.studentName").value(Student1.getStudentName()))
                .andExpect(jsonPath("$.courseId").isArray())
                .andExpect(jsonPath("$.courseId[0]").value(Student1.getCourseId().get(0)))
                .andExpect(jsonPath("$.courseId[1]").value(Student1.getCourseId().get(1)));

    }

    @Test
    void updateStudent() throws Exception {
        List<Long> courseDto = Arrays.asList(101l, 102l);
        StudentDto studentDto = new StudentDto(1l, "ManikCreated", courseDto);
        StudentDto updateStudent = new StudentDto(1l, "ManikUpdated",courseDto);

        when(studentService.updateStudent(Mockito.eq(1l),Mockito.any())).thenReturn(updateStudent);

        mockMvc.perform(put("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(studentDto.getId().longValue()))
                .andExpect(jsonPath("$.studentName").value(updateStudent.getStudentName()))
                .andExpect(jsonPath("$.courseId").isArray())
                .andExpect(jsonPath("$.courseId[0]").value(updateStudent.getCourseId().get(0)))
                .andExpect(jsonPath("$.courseId[1]").value(updateStudent.getCourseId().get(1)));
    }

    @Test
    void deleteStudent() throws Exception {
        List<Long> courseDto = Arrays.asList(100l,101l);
        StudentDto studentDto = new StudentDto(1l, "Manik", courseDto);

        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(studentService).deleteStudent(studentDto.getId().longValue());
    }

    @Test
    void getAllCourses() throws Exception {
        List<CoursesDto> coursesDtoList = Arrays.asList(
                new CoursesDto(101l, "Mathematics",Arrays.asList(1l,2l)),
                new CoursesDto(102l, "Science",Arrays.asList(3l,4l))

        );
        when(studentService.getAllCourses()).thenReturn(coursesDtoList);
        mockMvc.perform(get("/api/courses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(101))
                .andExpect(jsonPath("$[0].title").value("Mathematics"))
                .andExpect(jsonPath("$[0].studentId",containsInAnyOrder(1,2)))
                .andExpect(jsonPath("$[1].id").value(102))
                .andExpect(jsonPath("$[1].title").value("Science"))
                .andExpect(jsonPath("$[1].studentId",containsInAnyOrder(3,4)));
    }

    @Test
    void getCourseById() throws Exception {
        CoursesDto coursesDto = new CoursesDto(101l, "Science", Arrays.asList(1l, 2l));
        when(studentService.getCourseById(101l)).thenReturn(coursesDto);
        mockMvc.perform(get("/api/courses/101")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(coursesDto.getId().longValue()))
                .andExpect(jsonPath("$.title").value(coursesDto.getTitle()))
                .andExpect(jsonPath("$.studentId[0]").value(1))
                .andExpect(jsonPath("$.studentId[1]").value(2));
    }

    @Test
    void createCourse() throws Exception {
        CoursesDto coursesDto = new CoursesDto(10l,"SubjectCreated", Arrays.asList(10l, 20l));
        when(studentService.createCourses(Mockito.any())).thenReturn(coursesDto);
        mockMvc.perform(post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coursesDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(coursesDto.getId().longValue()))
                .andExpect(jsonPath("$.title").value("SubjectCreated"))
                .andExpect(jsonPath("$.studentId").isArray())
                .andExpect(jsonPath("$.studentId[0]").value(10))
                .andExpect(jsonPath("$.studentId[1]").value(20));
    }

    @Test
    void updateCourse() throws Exception {
        CoursesDto coursesDto = new CoursesDto(10l, "SubjectCreated", Arrays.asList(10l, 20l));
        CoursesDto updatedCourse = new CoursesDto(10l, "SubjectUpdated", Arrays.asList(10l, 20l));

        when(studentService.updateCourses(Mockito.eq(10l),Mockito.any())).thenReturn(updatedCourse);
        mockMvc.perform(put("/api/courses/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(coursesDto)))
                .andExpect(jsonPath("$.id").value(coursesDto.getId()))
                .andExpect(jsonPath("$.title").value(updatedCourse.getTitle()))
                .andExpect(jsonPath("$.studentId").isArray())
                .andExpect(jsonPath("$.studentId[0]").value(10))
                .andExpect(jsonPath("$.studentId[1]").value(20));

    }

    @Test
    void deleteCourse() throws Exception {
        CoursesDto coursesDto = new CoursesDto(10l, "Science", Arrays.asList(10l, 20l));
        mockMvc.perform(delete("/api/students/10"))
                .andExpect(status().isNoContent());
        Mockito.verify(studentService).deleteCourses(coursesDto.getId());

    }

    @Test
    void enrollStudentInCourse() {
    }
}