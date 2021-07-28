package com.example.jpademo.repository.springdatajpa;

import com.example.jpademo.entity.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
    /**
     * Custom queries
     */
    List<Course> findByName(String name);

    List<Course> findByNameAndId(String name, Long id);

    List<Course> findByNameOrderByLastUpdatedDateDesc(String name);

    long countByName(String name);

    void deleteByName(String name);

    @Query("Select c From Course c where name like '%100 Steps'")
    List<Course> courseWith100StepsInName();

    @Query(value = "Select * From Course where name like '%100 Steps'", nativeQuery = true)
    List<Course> courseWith100StepsInNameUsingNativeQuery();
}
