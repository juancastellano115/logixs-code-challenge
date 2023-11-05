package com.juancastellano.msregistrations.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.juancastellano.msregistrations.dto.Student;

@FeignClient(name = "STUDENTS-SERVICE")
public interface StudentClient {
    @GetMapping("/student/{studentId}")
    Student getStudent(@PathVariable("studentId") int studentId);
}