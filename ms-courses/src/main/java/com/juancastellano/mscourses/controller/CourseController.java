package com.juancastellano.mscourses.controller;

import com.juancastellano.mscourses.entity.Course;
import com.juancastellano.mscourses.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/course")
@Tag(name = "Course", description = "The Course Api")
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Operation(summary = "Add a new Course", description = "", tags = { "Course" })
    @ApiResponse(responseCode = "201", description = "Course created")
    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @Operation(summary = "Get all Courses", description = "", tags = { "Course" })
    @ApiResponse(responseCode = "200", description = "Courses fetched")
    @GetMapping
    public List<Course> fetchCourses() {
        return courseService.fetchCourses();
    }

    @Operation(summary = "Get a Course by Id", description = "", tags = { "Course" })
    @ApiResponse(responseCode = "200", description = "Course fetched")
    @GetMapping("/{id}")
    public Course fetchCourseById(@PathVariable Long id) {
        return courseService.fetchCourseById(id);
    }

    @Operation(summary = "Update a Course by Id", description = "", tags = { "Course" })
    @ApiResponse(responseCode = "200", description = "Course updated")
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @Operation(summary = "Delete a Course by Id", description = "", tags = { "Course" })
    @ApiResponse(responseCode = "200", description = "Course deleted")
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}