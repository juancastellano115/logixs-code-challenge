package com.juancastellano.msstudents.service;

import com.juancastellano.msstudents.entity.Student;
import com.juancastellano.msstudents.rabbitmq.MessageProducer;
import com.juancastellano.msstudents.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MessageProducer messageProducer;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> fetchStudents() {
        return studentRepository.findAll();
    }

    public Student fetchStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setSurname(student.getSurname());
            existingStudent.setBirthDate(student.getBirthDate());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
        messageProducer.sendMessageWithID("student-deleted-id:", String.valueOf(id));
    }

}
