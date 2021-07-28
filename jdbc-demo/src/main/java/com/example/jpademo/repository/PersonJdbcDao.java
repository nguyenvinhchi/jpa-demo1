package com.example.jpademo.repository;

import com.example.jpademo.entity.Person;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonJdbcDao {

  private final JdbcTemplate jdbcTemplate;

  public List<Person> findAll() {
    return jdbcTemplate.query("select * from person",
        new BeanPropertyRowMapper<Person>(Person.class));
  }

  public Person findById(String id) {
    return jdbcTemplate.queryForObject("select * from person where id=?",
        new BeanPropertyRowMapper<>(Person.class), id);
  }

  public List<Person> findByName(String name) {
    return jdbcTemplate.query("select * from person where name like '%" + name + "%'",
        new BeanPropertyRowMapper<>(Person.class));
  }

  public int create(Person person) {
    person.setId(UUID.randomUUID().toString());
    return jdbcTemplate.update(
        "insert into person (id, name, location, birth_date) VALUES(?, ?, ?, ?)",
        person.getId(), person.getName(), person.getLocation(),
        new Timestamp(person.getBirthDate().getTime()));

  }

  public int update(Person person) {
    return jdbcTemplate.update(
        "update person set name=?, location=?, birth_date=? where id=?",
        person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()),
        person.getId());

  }

  public int deleteById(String id) {
    return jdbcTemplate.update("delete from person where id=?", id);
  }
}
