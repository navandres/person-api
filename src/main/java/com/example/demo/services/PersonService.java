package com.example.demo.services;

import com.example.demo.exceptions.PersonDoesNotExistException;
import com.example.demo.exceptions.PersonExistException;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private PersonInterface personRepo;

    @Autowired
    public PersonService(PersonInterface personRepo) {
        this.personRepo = personRepo;
    }

    public Person addPerson(String name) throws PersonExistException {
        return personRepo.addPerson(name);
    }

    public Person getPerson(UUID id) throws PersonDoesNotExistException {
        Optional<Person> person = personRepo.getPerson(id);
        if (!person.isPresent())
            throw new PersonDoesNotExistException("Person with id " + id + " does not exist.");
        return person.get();
    }

    public Person updatePerson(UUID id, String name) throws PersonDoesNotExistException {
        return personRepo.updatePerson(id, name);
    }

    public boolean deletePerson(UUID id) throws PersonDoesNotExistException {
        return personRepo.deletePerson(id);
    }

    public List<Person> getAllPeople() {
        return personRepo.getAllPeople();
    }
}
