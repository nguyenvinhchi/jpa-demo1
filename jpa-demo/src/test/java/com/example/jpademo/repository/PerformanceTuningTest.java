package com.example.jpademo.repository;

import com.example.jpademo.entity.Course;
import com.example.jpademo.repository.springdatajpa.CourseSpringDataRepository;
import java.util.List;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class PerformanceTuningTest {
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void createNPlusOneProblem() {
        TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
        List<Course> courses = query.getResultList();
        for (Course c : courses) {
            log.info("Course -> {} Students -> {}", c, c.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvedNPlusOneProblem() {
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        entityGraph.addSubgraph("students");

        TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph);
        List<Course> courses = query.getResultList();
        for (Course c : courses) {
            log.info("Course -> {} Students -> {}", c, c.getStudents());
        }
    }

    @Test
    @Transactional
    public void solved_NPlusOneProblem_usingJoin() {
        TypedQuery<Course> query = em.createQuery("Select c From Course c JOIN FETCH c.students s", Course.class);
        List<Course> courses = query.getResultList();
        for (Course c : courses) {
            log.info("Course -> {} Students -> {}", c, c.getStudents());
        }
    }
}
