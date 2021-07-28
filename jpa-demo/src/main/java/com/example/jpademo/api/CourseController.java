package com.example.jpademo.api;

import com.example.jpademo.api.model.CourseRegister;
import com.example.jpademo.entity.Course;
import com.example.jpademo.entity.Review;
import com.example.jpademo.entity.Student;
import com.example.jpademo.repository.CourseRepository;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseRepository courseRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(courseRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        Course result = courseRepository.save(course);
        return ResponseEntity.created(null).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        Course entity = courseRepository.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        entity.setName(course.getName());
        courseRepository.save(entity);

        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        Long result = courseRepository.deleteById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{courseId}/reviews")
    public ResponseEntity<Review> addReview(@PathVariable Long courseId, @RequestBody Review review) {
        Review entity = courseRepository.addReview(courseId, review);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.created(null).body(entity);
    }

    @GetMapping("/{courseId}/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long courseId) {
        List<Review> reviews = courseRepository.getCourseAllReviews(courseId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/{courseId}/register")
    public ResponseEntity<?> register(@PathVariable Long courseId,
                                      @RequestBody CourseRegister courseRegister) {
        courseRepository.register(courseRegister);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<Set<Student>> getCourseStudents(@PathVariable Long courseId) {
        Set<Student> students = courseRepository.getCourseStudent(courseId);
        return ResponseEntity.ok(students);
    }
}
