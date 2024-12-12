package com.example.demo.entities;
import java.util.ArrayList;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "animal")
public class AnimalEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @ManyToOne
    @JoinColumn(name= "species_id")
    private SpecieEntity specie;

    @ManyToMany
    @JoinTable(
        name = "person_animals", 
        joinColumns = @JoinColumn(name = "animals_id"), 
        inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<PersonEntity> owners;

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSex() {
        return sex;
    }

    public String getSpecie() {
        return specie.getCommonName();
    }

    public AnimalEntity() {}

    public AnimalEntity(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public Set<PersonEntity> getOwners() {
        return owners;
    }
}
