package com.example.jpademo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.jpademo.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired CourseRepository repository;

    @Test
    void findById() {
        Course course = repository.findById(1001L);
        assertThat(course).isNotNull();
        assertThat(course.getName()).isEqualTo("JPA learning");
    }

    @Test
    @DirtiesContext
    void create() {
        Course course = new Course();
        course.setName("New Course");
        Course result = repository.save(course);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo("New Course");
    }

    @Test
    @DirtiesContext
    void update() {
        Course course = repository.findById(1001L);
        assertThat(course).isNotNull();
        assertThat(course.getName()).isEqualTo("JPA learning");

        course.setName("New Course");
        Course result = repository.save(course);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("New Course");
    }

    @Test
    @DirtiesContext
    void deleteById() {
        repository.deleteById(1001L);
        assertThat(repository.findById(1001L)).isNull();
    }

    @Test
    void deleteById_notfound() {
        Long result = repository.deleteById(10010L);
        assertThat(result).isNull();
    }
}