package com.example.demo.repositories.impl;

import com.example.demo.exceptions.PersonDoesNotExistException;
import com.example.demo.exceptions.PersonExistException;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonInterface;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PersonRepository implements PersonInterface {

    private List<Person> persons = new ArrayList<Person>();

    @Override
    public Person addPerson(String name) throws PersonExistException {
        Optional<Person> exsistingPerson =
                persons.stream()
                        .filter(p -> p.getName().equalsIgnoreCase(name) )
                        .findFirst();
        if (exsistingPerson.isPresent())
            throw new PersonExistException("A person with a name " + name + " already exsist.");

        UUID id = UUID.randomUUID();
        Person newPerson = new Person(id, name);
        persons.add(newPerson);
        return newPerson;
    }

    @Override
    public Optional<Person> getPerson(UUID id) {
        return persons.stream().filter( p -> p.getId().equals(id) ).findFirst();
    }

    @Override
    public Person updatePerson(UUID id, String name) throws PersonDoesNotExistException {
        Optional<Person> person = getPerson(id);
        if (!person.isPresent())
            throw new PersonDoesNotExistException("A person with id " + id + " does not exist.");

        person.get().setName(name);
        return person.get();
    }

    @Override
    public boolean deletePerson(UUID id) throws PersonDoesNotExistException {
        Optional<Person> personToDelete = getPerson(id);
        if (!personToDelete.isPresent())
            throw new PersonDoesNotExistException("A person with id " + id + " does not exist.");

        return persons.remove(personToDelete.get());
    }

    @Override
    public List<Person> getAllPeople() {
        return persons;
    }

}
