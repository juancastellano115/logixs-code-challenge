package com.juancastellano.mscourses.service;

import com.juancastellano.mscourses.repository.CourseRepository;
import com.juancastellano.mscourses.entity.Course;
import com.juancastellano.mscourses.rabbitmq.MessageProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MessageProducer messageProducer;

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> fetchCourses() {
        return courseRepository.findAll();
    }

    public Course fetchCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            course.setName(updatedCourse.getName());
            course.setDescription(updatedCourse.getDescription());
            // Update other properties as needed
            return courseRepository.save(course);
        }
        return null;
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
        messageProducer.sendMessageWithID("course-deleted-id:", String.valueOf(id));
    }
}