package com.juancastellano.msregistrations.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
}