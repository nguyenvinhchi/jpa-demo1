package com.example.jpademo.repository.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.jpademo.entity.Course;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Slf4j
class CourseSpringDataRepositoryTest {
    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById() {
        Optional<Course> courseOptional = repository.findById(1001L);
        assertThat(courseOptional).isPresent();
    }

    @Test
    public void insert_update() {
        Course course = new Course();
        course.setName("New test course");
        repository.save(course);

        Long id = course.getId();
        assertThat(id).isNotNull();
        course.setName("New test course updated");
        repository.save(course);
    }

    @Test
    public void deleteById() {
        Course course = new Course();
        course.setName("New test course");
        repository.save(course);

        Long id = course.getId();
        assertThat(id).isNotNull();
        repository.deleteById(id);
        Optional<Course> courseOptional = repository.findById(id);
        assertThat(courseOptional).isNotPresent();
    }

    @Test
    public void sort() {
        Sort sort = Sort.by(Sort.Direction.fromString("asc"), "name");
        List<Course> courses = repository.findAll(sort);
        log.info("{}", courses);
    }

    @Test
    public void page() {
        Course course1 = new Course();
        course1.setName("A course 1");
        Course course2 = new Course();
        course2.setName("B course 2");
        Course course3 = new Course();
        course3.setName("C course 3");
        repository.saveAll(Arrays.asList(course3, course2, course1));

        Sort sort = Sort.by(Sort.Direction.fromString("asc"), "name");
        PageRequest pageRequest = PageRequest.of(0, 2, sort);
        Page<Course> firstPage = repository.findAll(pageRequest);
        for (Course c: firstPage ) {
            log.info("{}", c);
        }

        Page<Course> secondPage = repository.findAll(pageRequest.next());
        for (Course c: secondPage ) {
            log.info("{}", c);
        }
    }
}