package com.juancastellano.msregistrations.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registration")
@IdClass(RegistrationId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    @Id
    private Long studentId;
    @Id
    private Long courseId;

    @Column(nullable = false)
    private Date registrationDate = new Date();

}