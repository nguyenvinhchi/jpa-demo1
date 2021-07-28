package com.example.jpademo.api;

import com.example.jpademo.entity.Person;
import com.example.jpademo.repository.PersonJdbcDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {
  private final PersonJdbcDao personJdbcDao;

  @GetMapping
  public ResponseEntity<List<Person>> getAll(@RequestParam(required = false) String name) {
    List<Person> persons;
    if (name == null) {
      persons = personJdbcDao.findAll();
    } else {
      persons= personJdbcDao.findByName(name);
    }
    return ResponseEntity.ok(persons);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> findById(@PathVariable String id) {
    try {
      Person person = personJdbcDao.findById(id);
      return ResponseEntity.ok(person);
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Person person) {
    int count = personJdbcDao.create(person);
    if (count == 1) {
      return ResponseEntity.created(null).body(null);
    }
    throw new RuntimeException("Failed to create person");
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody Person person) {
    person.setId(id);
    int count = personJdbcDao.update(person);
    if (count == 0) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(null);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    int count = personJdbcDao.deleteById(id);
    if (count == 0) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(null);
  }
}
