package com.example.jpademo.repository;

import com.example.jpademo.api.model.CourseRegister;
import com.example.jpademo.entity.Course;
import com.example.jpademo.entity.Review;
import com.example.jpademo.entity.Student;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CourseRepository {
    private final EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    @Transactional
    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    @Transactional
    public Long deleteById(Long id) {
        Course entity = findById(id);
        if (entity == null) {
            return null;
        }
        em.remove(entity);
        return id;
    }

    public List<Course> findByName(String name) {
        TypedQuery<Course> query = em.createQuery("select c from Course c where name like '%:name%'", Course.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Course> findAll() {
        TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);
        return query.getResultList();
    }

    @Transactional
    public Review addReview(Long courseId, Review review) {
        Course course = findById(courseId);
        if (course == null) {
            return null;
        }
        course.addReview(review);
        review.setCourse(course);
        em.persist(review);
        return review;
    }

    public List<Review> getCourseAllReviews(Long courseId) {
        Course course = findById(courseId);
        if (course == null) {
            return Collections.emptyList();
        }
        return course.getReviews();
    }

    @Transactional
    public void register(CourseRegister courseRegister) {
        Student student = em.find(Student.class, courseRegister.getStudentId());
        if (student == null) {
            throw new RuntimeException("Student not found!");
        }
        Course course = em.find(Course.class, courseRegister.getCourseId());
        if (course == null) {
            throw new RuntimeException("Course not found!");
        }
        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
    }

    public Set<Student> getCourseStudent(Long courseId) {
        Course course = em.find(Course.class, courseId);
        if (course == null) {
            throw new RuntimeException("Course not found!");
        }
        return  course.getStudents();
    }
}
