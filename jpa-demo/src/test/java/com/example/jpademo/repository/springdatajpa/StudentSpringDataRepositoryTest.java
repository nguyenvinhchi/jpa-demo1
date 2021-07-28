package com.example.jpademo.repository.springdatajpa;

import com.example.jpademo.entity.Address;
import com.example.jpademo.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentSpringDataRepositoryTest {
    @Autowired
    StudentSpringDataRepository repository;

    @Test
    public void create_withAddress() {
        Student student = new Student();
        student.setName("Andrew C");
        Address address = new Address();
        address.setLine1("Add line1");
        address.setCity("Hanoi");
        student.setAddress(address);
        repository.save(student);
    }
}