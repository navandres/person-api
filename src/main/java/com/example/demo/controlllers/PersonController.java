package com.example.demo.controlllers;

import com.example.demo.exceptions.PersonDoesNotExistException;
import com.example.demo.exceptions.PersonExistException;
import com.example.demo.model.Person;
import com.example.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/person")
@RestController
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "{id}")
    public Person getPerson(@PathVariable("id") UUID id) throws PersonDoesNotExistException {
        return personService.getPerson(id);
    }

    @PutMapping
    public Person addPerson(@RequestBody Person person) throws PersonExistException {
        return personService.addPerson(person.getName());
    }

    @PatchMapping
    public Person updatePerson(@RequestBody Person person) throws PersonDoesNotExistException {
        return personService.updatePerson(person.getId(), person.getName());
    }

    @DeleteMapping(path = "{id}")
    public void deletePerson(@PathVariable("id") UUID id) throws PersonDoesNotExistException {
        personService.deletePerson(id);
    }

    @GetMapping("all")
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

}
