package com.example.demo.entities;
import java.util.ArrayList;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @ManyToMany (mappedBy = "owners")
    private Set<AnimalEntity> pets;
    
    public PersonEntity() {}

    public PersonEntity(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getPerson() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        return age;
    }
}
