package com.juancastellano.mscourses.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @SequenceGenerator(name = "course_id_sequence", sequenceName = "course_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_sequence")
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
}