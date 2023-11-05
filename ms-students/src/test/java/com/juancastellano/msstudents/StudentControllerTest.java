package com.juancastellano.msstudents;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juancastellano.msstudents.controller.StudentController;
import com.juancastellano.msstudents.entity.Student;
import com.juancastellano.msstudents.service.StudentService;

@WebMvcTest(StudentController.class)
@ContextConfiguration(classes = { StudentController.class })
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @MockBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchStudentById() throws Exception {
        Student student = new Student();
        student.setId((long) 1);
        student.setName("John Doe");
        student.setSurname("Garcia");

        when(studentService.fetchStudentById((long) 1)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(student)));
    }

    @Test
    public void testFetchStudents() throws Exception {
        Student student1 = new Student();
        student1.setId((long) 1);
        student1.setName("John Doe");
        student1.setSurname("Garcia");

        Student student2 = new Student();
        student2.setId((long) 2);
        student2.setName("Jane Doe");
        student2.setSurname("Garcia");

        List<Student> students = Arrays.asList(student1, student2);

        when(studentService.fetchStudents()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/student"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(students)));
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setName("John Doe");
        student.setSurname("Garcia");
        student.setBirthDate(new Date());

        when(studentService.createStudent(student)).thenReturn(student);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(student);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        if (!content.isEmpty()) {
            Student responseStudent = mapper.readValue(content, Student.class);

            assertEquals(student.getId(), responseStudent.getId());
            assertEquals(student.getName(), responseStudent.getName());
            assertEquals(student.getSurname(), responseStudent.getSurname());
        }
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = new Student();
        student.setId((long) 1);
        student.setName("John Doe");
        student.setSurname("Garcia");

        when(studentService.updateStudent((long) 1, student)).thenReturn(student);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(student);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/student/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        if (!content.isEmpty()) {
            Student responseStudent = mapper.readValue(content, Student.class);

            assertEquals(student.getId(), responseStudent.getId());
            assertEquals(student.getName(), responseStudent.getName());
            assertEquals(student.getSurname(), responseStudent.getSurname());
        }
    }

    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }
}