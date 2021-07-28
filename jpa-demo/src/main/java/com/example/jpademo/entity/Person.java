package com.example.jpademo.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import lombok.Data;

@Entity
@Data
@NamedQuery(name = "find_all_persons", query = "select p from Person p")
@NamedQuery(name = "find_by_name", query = "select p from Person p where p.name like :likeNamePattern")
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String location;
    private Date birthDate;
}
