package com.example.jpademo.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Person {
  private Long id;
  private String name;
  private String location;
  private Date birthDate;
}
