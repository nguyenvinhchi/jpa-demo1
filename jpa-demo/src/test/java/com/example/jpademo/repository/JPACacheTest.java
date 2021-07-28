package com.example.jpademo.repository;

import com.example.jpademo.entity.Course;
import com.example.jpademo.repository.springdatajpa.CourseSpringDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class JPACacheTest {
    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void test_withoutFirstLevelCache() {
        Course course1 = repository.findById(1001L).orElseThrow(() -> new RuntimeException("NOT FOUND"));
        log.info("first findById for 1001 {}", course1);
        Course course2 = repository.findById(1001L).orElseThrow(() -> new RuntimeException("NOT FOUND"));
        log.info("second findById for 1001 {}", course2);
    }

    @Test
    @Transactional
    public void test_firstLevelCache_InTransactional() {
        Course course1 = repository.findById(1001L).orElseThrow(() -> new RuntimeException("NOT FOUND"));
        log.info("first findById for 1001 {}", course1);
        Course course2 = repository.findById(1001L).orElseThrow(() -> new RuntimeException("NOT FOUND"));
        log.info("second findById for 1001 {}", course2);
    }
}
