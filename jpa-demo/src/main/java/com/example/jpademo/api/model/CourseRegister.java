package com.example.jpademo.api.model;

import lombok.Data;

@Data
//@JsonNaming(value = SnakeCaseStrategy.class)
public class CourseRegister {
    private Long courseId;
    private Long studentId;
}
