package com.example.jpademo.entity;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private String line1;
    private String line2;
    private String city;
}
