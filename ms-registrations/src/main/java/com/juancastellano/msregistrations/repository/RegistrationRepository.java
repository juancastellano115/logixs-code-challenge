package com.juancastellano.msregistrations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juancastellano.msregistrations.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    Registration findByStudentIdAndCourseId(int studentId, int courseId);

    void deleteAllByCourseId(int courseId);

    void deleteAllByStudentId(int studentId);

    List<Registration> findAllByStudentId(int studentId);

    List<Registration> findAllByCourseId(int courseId);

}
