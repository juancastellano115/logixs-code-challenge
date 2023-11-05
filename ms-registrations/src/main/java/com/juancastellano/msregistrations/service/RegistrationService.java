package com.juancastellano.msregistrations.service;

import com.juancastellano.msregistrations.dto.Course;
import com.juancastellano.msregistrations.dto.Student;
import com.juancastellano.msregistrations.entity.Registration;
import com.juancastellano.msregistrations.repository.RegistrationRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.juancastellano.msregistrations.client.CourseClient;
import com.juancastellano.msregistrations.client.StudentClient;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CourseClient courseClient;

    @Autowired
    private StudentClient studentClient;

    public List<Registration> fetchRegistrations() {
        return registrationRepository.findAll();
    }

    public ResponseEntity<?> subscribeStudentToCourse(Long studentId, Long courseId) {
        if (!checkCourseId(courseId)) {
            return new ResponseEntity<>("Invalid courseId", HttpStatus.BAD_REQUEST);
        }

        if (!checkStudentId(studentId)) {
            return new ResponseEntity<>("Invalid studentId", HttpStatus.BAD_REQUEST);
        }

        Registration registration = new Registration();
        registration.setStudentId(studentId);
        registration.setCourseId(courseId);

        return new ResponseEntity<>(registrationRepository.save(registration), HttpStatus.CREATED);
    }

    public ResponseEntity<?> unsubscribeStudentFromCourse(Long studentId, Long courseId) {
        if (!checkCourseId(courseId)) {
            return new ResponseEntity<>("Invalid courseId", HttpStatus.BAD_REQUEST);
        }

        if (!checkStudentId(studentId)) {
            return new ResponseEntity<>("Invalid studentId", HttpStatus.BAD_REQUEST);
        }

        Registration registration = registrationRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (registration != null) {
            registrationRepository.delete(registration);
            return new ResponseEntity<>("Unsubscribed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No registration found", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public void unsubscribeAllStudentsFromCourse(Long courseId) {
            registrationRepository.deleteAllByCourseId(courseId);
    }

    @Transactional
    public void unsubscribeAllCoursesFromStudent(Long studentId) {
        registrationRepository.deleteAllByStudentId(studentId);
    }

    public Boolean checkStudentHasRegistrations(Long studentId) {
        return registrationRepository.findAllByStudentId(studentId).size() > 0;
    }

    public Boolean checkCourseHasRegistrations(Long courseId) {
        return registrationRepository.findAllByCourseId(courseId).size() > 0;
    }

    private Boolean checkCourseId(Long courseId) {
        Course course = courseClient.getCourse(courseId);
        return course != null;
    }

    private Boolean checkStudentId(Long studentId) {
        Student student = studentClient.getStudent(studentId);
        return student != null;
    }
}