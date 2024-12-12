package com.example.demo.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.AnimalEntity;

@Repository
public interface AnimalRepository extends CrudRepository<AnimalEntity, Long> {

    Collection<AnimalEntity> findBySex(String sex);
    
    AnimalEntity findByName(String name);
}
