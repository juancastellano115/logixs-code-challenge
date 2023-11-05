package com.juancastellano.mscourses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juancastellano.mscourses.controller.CourseController;
import com.juancastellano.mscourses.entity.Course;
import com.juancastellano.mscourses.service.CourseService;

@WebMvcTest(CourseController.class)
@ContextConfiguration(classes = { CourseController.class })
@ActiveProfiles("test")
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCourse() throws Exception {
        Course course = new Course();
        course.setId((long) 1);
        course.setName("Test Course");
        course.setDescription("Test Description");

        when(courseService.addCourse(any(Course.class))).thenReturn(course);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(course);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Course responseCourse = mapper.readValue(content, Course.class);

        assertEquals(course.getId(), responseCourse.getId());
        assertEquals(course.getName(), responseCourse.getName());
        assertEquals(course.getDescription(), responseCourse.getDescription());
    }

    @Test
    public void testFetchCourses() throws Exception {
        List<Course> courses = new ArrayList<>();
        Course course1 = new Course();
        course1.setId((long) 1);
        course1.setName("Test Course 1");
        course1.setDescription("Test Description 1");
        courses.add(course1);

        Course course2 = new Course();
        course2.setId((long) 2);
        course2.setName("Test Course 2");
        course2.setDescription("Test Description 2");
        courses.add(course2);

        when(courseService.fetchCourses()).thenReturn(courses);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(courses);

        mockMvc.perform(MockMvcRequestBuilders.get("/course")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void testFetchCourseById() throws Exception {
        Course course = new Course();
        course.setId((long) 1);
        course.setName("Test Course");
        course.setDescription("Test Description");

        when(courseService.fetchCourseById((long) 1)).thenReturn(course);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(course);

        mockMvc.perform(MockMvcRequestBuilders.get("/course/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void testUpdateCourse() throws Exception {
        Course course = new Course();
        course.setId((long) 1);
        course.setName("Test Course");
        course.setDescription("Test Description");

        when(courseService.updateCourse((long) eq(1), any(Course.class))).thenReturn(course);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(course);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/course/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        if (!content.isEmpty()) {
            Course responseCourse = mapper.readValue(content, Course.class);

            assertEquals(course.getId(), responseCourse.getId());
            assertEquals(course.getName(), responseCourse.getName());
            assertEquals(course.getDescription(), responseCourse.getDescription());
        }
    }

    @Test
    public void testDeleteCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/course/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}