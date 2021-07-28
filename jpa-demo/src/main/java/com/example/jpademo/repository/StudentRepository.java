package com.example.jpademo.repository;

import com.example.jpademo.entity.Course;
import com.example.jpademo.entity.Passport;
import com.example.jpademo.entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private final EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    @Transactional
    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    @Transactional
    public Long deleteById(Long id) {
        Student entity = findById(id);
        if (entity == null) {
            return null;
        }
        em.remove(entity);
        return id;
    }

    public List<Student> findByName(String name) {
        TypedQuery<Student> query = em.createQuery("select c from Student c where name like '%:name%'", Student.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery("select c from Student c", Student.class);
        return query.getResultList();
    }

    @Transactional
    public void doSomething() {
        Student student = em.find(Student.class, 1001L);
        Passport passport = student.getPassport();
        passport.setNumber("A6543210");
        student.setName("Chi Nguyen");

    }

    public Student addCourse(Long studentId, Long courseId) {
        Student student = em.find(Student.class, studentId);
        if (student == null) {
            return null;
        }
        Course course = em.find(Course.class, courseId);
        if (course == null) {
            throw new RuntimeException("Course not found!");
        }
        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);

        return student;
    }
}
