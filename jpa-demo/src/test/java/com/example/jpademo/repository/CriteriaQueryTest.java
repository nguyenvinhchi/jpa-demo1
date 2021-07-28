package com.example.jpademo.repository;

import com.example.jpademo.entity.Course;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class CriteriaQueryTest {
    @Autowired
    EntityManager em;

    @Test
    public void test_criteriaQuery_like() {

        //select c From Course c like '%100 Steps'

        //Use Criteria Builder to create Criteria Query returning expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //Define roots used in query
        Root<Course> courseRoot = cq.from(Course.class);

        //Define predicates
        Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");

        // Add predicates to CQ
        cq.where(like100Steps);

        // build TypedQuery
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();
    }

    @Test
    public void test_criteriaQuery_without() {

        //select c From Course c where c.students is empty'

        //Use Criteria Builder to create Criteria Query returning expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //Define roots used in query
        Root<Course> courseRoot = cq.from(Course.class);

        //Define predicates
        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));

        // Add predicates to CQ
        cq.where(studentsIsEmpty);

        // build TypedQuery
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();
    }

    @Test
    @Transactional
    public void test_criteriaQuery_join() {

        //select c From Course c join c.students'

        //Use Criteria Builder to create Criteria Query returning expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //Define roots used in query
        Root<Course> courseRoot = cq.from(Course.class);

        //Define predicates
        Join<Object, Object> joinStudents = courseRoot.join("students");

        // Add predicates to CQ

        // build TypedQuery
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();

        log.info("{}", courses);
    }

    @Test
    @Transactional
    public void test_criteriaQuery_left_join() {

        //select c From Course c left join c.students'

        //Use Criteria Builder to create Criteria Query returning expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //Define roots used in query
        Root<Course> courseRoot = cq.from(Course.class);

        //Define predicates
        courseRoot.join("students", JoinType.LEFT);

        // Add predicates to CQ

        // build TypedQuery
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();

        log.info("{}", courses);
    }
}
