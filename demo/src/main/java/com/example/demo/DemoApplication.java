package com.example.demo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.AnimalEntity;
import com.example.demo.entities.PersonEntity;
import com.example.demo.entities.SpecieEntity;
import com.example.demo.repositories.AnimalRepository;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.SpeciesRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {	

	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private SpeciesRepository speciesRepository;

	@Autowired
	private PersonRepository personRepository;
@Override
public void run(String... args) throws Exception {

	Iterable<AnimalEntity> animalList = this.animalRepository.findAll();
	System.out.println("Tous les ptits animaux:");
	for (AnimalEntity animalEntity : animalList) {
		System.out.println(animalEntity.getName() + " le " + animalEntity.getSpecie());
	}

	// TP05 - Species
	// Ordoner les espèces de Z à A
	Iterable<SpecieEntity> speciesList = this.speciesRepository.findAllOrderedByNameDesc();
	System.out.println("Toutes les entités Species : ");
	for (SpecieEntity specieEntity : speciesList) {
		System.out.println(specieEntity.getCommonName());
	}

	// Voir toutes les espèces dont le nom correspond à la recherche
	Iterable<SpecieEntity> specieByName = this.speciesRepository.findByCommonNameLike("C%");
	System.out.println("Toutes les entités Species: ");
	for (SpecieEntity specieEntity : specieByName) {
		System.out.println(specieEntity.getCommonName());
	}

	// TP05 - Person
	// Les personnes entre un age minimum et un age maximum
	int ageMin = 15;
	int ageMax = 40;
	Iterable<PersonEntity> personByAge = this.personRepository.findByAgeGreaterThanEqualAndAgeLessThanEqual(ageMin, ageMax);
	System.out.println("Toutes les utilisateurs entre : " + ageMin + " et " + ageMax);
	for (PersonEntity personEntity : personByAge) {
		System.out.println(personEntity.getPerson() + ", " + personEntity.getAge() + " ans");
	}

	//Personnes selon les animaux qu'ils possèdent
	SpecieEntity specieEntity = this.speciesRepository.findByCommonName("Lapin");
	Iterable<PersonEntity> personByAnimal = this.personRepository.findByAnimalOwned(specieEntity);
	System.out.println("Les personnes qui ont un " + specieEntity.getCommonName());
	for (PersonEntity personEntity : personByAnimal) {
		System.out.println(personEntity.getPerson());
	}


	// TP05 - Animal
	// Les animaux selon leur sexe
	String sex = "F";
	Iterable<AnimalEntity> animalListBySex = this.animalRepository.findBySex(sex);
	System.out.println("Toutes les entités Animal de sexe " + sex);
	for (AnimalEntity animalEntity : animalListBySex) {
		System.out.println(animalEntity.getName() + " le " + animalEntity.getSpecie() + " " + animalEntity.getSex());
	}

	//Est-ce que cet animal possède un maître ?
	AnimalEntity littleAnimal = this.animalRepository.findByName("Lou");
	System.out.println(this.personRepository.doesThisPetHasAnOwner(littleAnimal.getName()));
}

public static void main(String[] args) {
	SpringApplication.run(DemoApplication.class, args);
}
}
