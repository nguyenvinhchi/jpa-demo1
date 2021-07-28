package com.example.jpademo.repository.springdatajpa;

import com.example.jpademo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSpringDataRepository extends JpaRepository<Student, Long> {
}
