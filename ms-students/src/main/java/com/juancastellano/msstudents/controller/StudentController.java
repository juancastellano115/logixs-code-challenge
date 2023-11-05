package com.juancastellano.msstudents.controller;

import com.juancastellano.msstudents.entity.Student;
import com.juancastellano.msstudents.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@Tag(name = "Student", description = "The Student API")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Operation(summary = "Get a student by Id", description = "", tags = { "Student" })
    @ApiResponse(responseCode = "200", description = "Student fetched")
    @GetMapping("/{id}")
    public Student fetchStudentById(@PathVariable Long id) {
        return studentService.fetchStudentById(id);
    }

    @Operation(summary = "Get all students", description = "", tags = { "Student" })
    @ApiResponse(responseCode = "200", description = "Students fetched")
    @GetMapping
    public List<Student> fetchStudents() {
        return studentService.fetchStudents();
    }

    @Operation(summary = "Create a student", description = "", tags = { "Student" })
    @ApiResponse(responseCode = "201", description = "Student created")
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @Operation(summary = "Update a student", description = "", tags = { "Student" })
    @ApiResponse(responseCode = "200", description = "Student updated")
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @Operation(summary = "Delete a student", description = "", tags = { "Student" })
    @ApiResponse(responseCode = "200", description = "Student deleted")
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

}