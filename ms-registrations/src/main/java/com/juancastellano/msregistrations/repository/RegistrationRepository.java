package com.juancastellano.msregistrations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juancastellano.msregistrations.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Registration findByStudentIdAndCourseId(Long studentId, Long courseId);

    void deleteAllByCourseId(Long courseId);

    void deleteAllByStudentId(Long studentId);

    List<Registration> findAllByStudentId(Long studentId);

    List<Registration> findAllByCourseId(Long courseId);

}
