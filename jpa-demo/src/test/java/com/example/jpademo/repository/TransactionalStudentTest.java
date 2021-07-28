package com.example.jpademo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.jpademo.entity.Course;
import com.example.jpademo.entity.Passport;
import com.example.jpademo.entity.Review;
import com.example.jpademo.entity.Student;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionalStudentTest {
    @Autowired
    EntityManager em;

    @Test
//    @Transactional
    public void test_transactional() {
        Student student = em.find(Student.class, 1001L);
        Passport passport = student.getPassport();
        passport.setNumber("A6543210");
        student.setName("Chi Nguyen");
    }

    @Test
    public void test_manyToOne() {
        Review review = em.find(Review.class, 1001L);
        assertThat(review).isNotNull();
        Course course = review.getCourse();
    }
}
