package com.example.jpademo.repository;

import com.example.jpademo.entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class PersonRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Person> findAll() {
        TypedQuery<Person> find_all_persons = entityManager.createNamedQuery("find_all_persons", Person.class);
        return find_all_persons.getResultList();
    }

    public List<Person> findByName(String name) {
        TypedQuery<Person> find_by_name = entityManager.createNamedQuery("find_by_name", Person.class);
        find_by_name.setParameter("likeNamePattern", "%" + name + "%");
        return find_by_name.getResultList();
    }

    public Person findById(Long id) {
        return entityManager.find(Person.class, id);
    }

    public Person create(Person person) {
        return entityManager.merge(person);
    }

    public Person update(Person person) {
        return entityManager.merge(person);
    }

    public Person deleteById(Long id) {
        Person person = findById(id);
        if (person == null) {
            return null;
        }
        entityManager.remove(person);
        return person;
    }
}
