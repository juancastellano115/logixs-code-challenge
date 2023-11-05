package com.juancastellano.msregistrations.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.juancastellano.msregistrations.dto.Course;

@FeignClient(name = "COURSES-SERVICE")
public interface CourseClient {
    @GetMapping("/course/{courseId}")
    Course getCourse(@PathVariable("courseId") int courseId);
}