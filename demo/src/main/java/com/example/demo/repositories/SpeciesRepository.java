package com.example.demo.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.SpecieEntity;

@Repository
public interface SpeciesRepository extends CrudRepository<SpecieEntity, Long> {

    @Query("FROM SpecieEntity s ORDER BY s.commonName DESC")
    Collection<SpecieEntity> findAllOrderedByNameDesc();

    Collection<SpecieEntity> findByCommonNameLike(String commonName);

    SpecieEntity findByCommonName(String commonName);
}
