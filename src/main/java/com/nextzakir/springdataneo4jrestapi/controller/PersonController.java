package com.nextzakir.springdataneo4jrestapi.controller;

import com.nextzakir.springdataneo4jrestapi.exception.InternalServerErrorException;
import com.nextzakir.springdataneo4jrestapi.repository.PersonRepository;
import com.nextzakir.springdataneo4jrestapi.node.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/save/")
    public ResponseEntity<Person> savePerson(@RequestParam("personId") Long personId,
                                             @RequestParam("personName") String personName) {

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonName(personName);
        person.setCreatedAt(new Date(Calendar.getInstance().getTimeInMillis()));
        person.setUpdatedAt(new Date(Calendar.getInstance().getTimeInMillis()));

        try {
            person = personRepository.save(person);
        } catch (Exception e) {
            logger.error("Error in saving person information. ERROR: " + e.getMessage());
            throw new InternalServerErrorException("Something went wrong on the server!");
        }

        return new ResponseEntity<>(person, HttpStatus.CREATED);

    }

}