package com.example.jpademo.api;

import com.example.jpademo.entity.Course;
import com.example.jpademo.repository.springdatajpa.CourseSpringDataRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springdata/courses")
@RequiredArgsConstructor
public class CourseSpringDataJpaController {
    private final CourseSpringDataRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        Optional<Course> courseOptional = repository.findById(id);
        return courseOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        Course result = repository.save(course);
        return ResponseEntity.created(null).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        Optional<Course> courseOptional = repository.findById(id);
        if (!courseOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Course entity = courseOptional.get();
        entity.setName(course.getName());
        repository.save(entity);

        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Course>> findAll(Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable));
    }
}
