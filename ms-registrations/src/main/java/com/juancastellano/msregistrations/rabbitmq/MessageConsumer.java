package com.juancastellano.msregistrations.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juancastellano.msregistrations.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageConsumer {

    private enum Entity {
        STUDENT,
        COURSE
    }

    @Autowired
    private RegistrationService registrationService;

    @RabbitListener(queues = "deleted-students-queue")
    public void handleDeletedStudentMessage(String message) {
        handleDeletedMessage(message, "Student");
    }

    @RabbitListener(queues = "deleted-courses-queue")
    public void handleDeletedCourseMessage(String message) {
        handleDeletedMessage(message, "Course");
    }

    private void handleDeletedMessage(String message, String entityName) {
        // Extract the ID from the message
        String[] parts = message.split(":");
        if (parts.length == 2) {
            String content = parts[0];
            String messageId = parts[1];
            // Do something with the content and ID
            log.info("Message received: " + content + " with ID: " + messageId);

            if (entityName.toUpperCase().equals(Entity.STUDENT.toString()) ) {
                // Check if the student has any registration
                if (registrationService.checkStudentHasRegistrations(Long.parseLong(messageId))) {
                    log.info("Student has registrations, unsubscribing...");
                    // If so, unsubscribe the student from all courses
                    registrationService.unsubscribeAllCoursesFromStudent(Long.parseLong(messageId));
                }
            } else if (entityName.toUpperCase().equals(Entity.COURSE.toString())) {
                // Check if the course has any registration
                if (registrationService.checkCourseHasRegistrations(Long.parseLong(messageId))) {
                    log.info("Course has registrations, unsubscribing...");
                    // If so, unsubscribe all students from the course
                    registrationService.unsubscribeAllStudentsFromCourse(Long.parseLong(messageId));
                }
            } else {
                log.error("Invalid entity name: " + entityName);

            }

        } else {
            log.error("Invalid message received: " + message);
        }
    }
}
