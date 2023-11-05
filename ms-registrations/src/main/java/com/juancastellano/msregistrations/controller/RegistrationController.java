package com.juancastellano.msregistrations.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juancastellano.msregistrations.service.RegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.juancastellano.msregistrations.entity.Registration;

@CrossOrigin("*")
@RestController
@Tag(name = "Registration", description = "The Registrations API")
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Operation(summary = "Get all registrations", description = "", tags = { "Registration" })
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping()
    public List<Registration> fetchRegistrations() {
        return registrationService.fetchRegistrations();
    }

    @Operation(summary = "Subscribe student to course", description = "", tags = { "Registration" })
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribeStudentToCourse(@RequestBody Map<String, Integer> request) {
        int studentId = request.get("studentId");
        int courseId = request.get("courseId");
        return registrationService.subscribeStudentToCourse(studentId, courseId);
    }

    @Operation(summary = "Unsubscribe student from course", description = "", tags = { "Registration" })
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @DeleteMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribeStudentFromCourse(@RequestBody Map<String, Integer> request) {
        int studentId = request.get("studentId");
        int courseId = request.get("courseId");
        return registrationService.unsubscribeStudentFromCourse(studentId, courseId);
    }

}