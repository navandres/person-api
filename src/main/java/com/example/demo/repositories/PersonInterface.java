package com.example.demo.repositories;

import com.example.demo.exceptions.PersonDoesNotExistException;
import com.example.demo.exceptions.PersonExistException;
import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonInterface {

    Person addPerson(String name) throws PersonExistException;

    Optional<Person> getPerson(UUID ip);

    Person updatePerson(UUID id, String name) throws PersonDoesNotExistException;

    boolean deletePerson(UUID id) throws PersonDoesNotExistException;

    List<Person> getAllPeople();
}
