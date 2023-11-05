package com.juancastellano.msregistrations;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.juancastellano.msregistrations.controller.RegistrationController;
import com.juancastellano.msregistrations.entity.Registration;
import com.juancastellano.msregistrations.service.RegistrationService;

public class RegistrationControllerTest {

    @Mock
    private RegistrationService registrationService;

    @InjectMocks
    private RegistrationController registrationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchRegistrations() {
        List<Registration> registrations = new ArrayList<>();
        Registration registration1 = new Registration();
        registration1.setStudentId(1);
        registration1.setCourseId(1);
        registrations.add(registration1);
        Registration registration2 = new Registration();
        registration2.setStudentId(2);
        registration2.setCourseId(2);
        registrations.add(registration2);

        when(registrationService.fetchRegistrations()).thenReturn(registrations);

        List<Registration> result = registrationController.fetchRegistrations();

        assert(result.size() == 2);
        assert(result.get(0).getStudentId() == 1);
        assert(result.get(0).getCourseId() == 1);
        assert(result.get(1).getStudentId() == 2);
        assert(result.get(1).getCourseId() == 2);
    }

    @Test
    public void testSubscribeStudentToCourse() {
        Map<String, Integer> request = new HashMap<>();
        request.put("studentId", 1);
        request.put("courseId", 1);

        when(registrationService.subscribeStudentToCourse(anyInt(), anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        ResponseEntity<?> result = registrationController.subscribeStudentToCourse(request);

        assert(result.getStatusCode() == HttpStatus.CREATED);
    }

    @Test
    public void testUnsubscribeStudentFromCourse() {
        Map<String, Integer> request = new HashMap<>();
        request.put("studentId", 1);
        request.put("courseId", 1);

        when(registrationService.unsubscribeStudentFromCourse(anyInt(), anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> result = registrationController.unsubscribeStudentFromCourse(request);

        assert(result.getStatusCode() == HttpStatus.OK);
    }

}