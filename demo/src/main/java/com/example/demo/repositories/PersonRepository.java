package com.example.demo.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.PersonEntity;
import com.example.demo.entities.SpecieEntity;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    Collection<PersonEntity> findByAgeGreaterThanEqualAndAgeLessThanEqual(int ageMin, int ageMax);

    @Query("FROM PersonEntity p JOIN p.pets a WHERE  a.specie = ?1")
    Collection<PersonEntity> findByAnimalOwned(SpecieEntity specie);

    @Query("SELECT EXISTS (SELECT p FROM PersonEntity p JOIN p.pets a WHERE a.name = ?1)")
    Boolean doesThisPetHasAnOwner(String petName);
}
