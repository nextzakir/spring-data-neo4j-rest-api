package com.nextzakir.springdataneo4jrestapi.controller;

import com.nextzakir.springdataneo4jrestapi.exception.InternalServerErrorException;
import com.nextzakir.springdataneo4jrestapi.exception.NotFoundException;
import com.nextzakir.springdataneo4jrestapi.helper.Helper;
import com.nextzakir.springdataneo4jrestapi.repository.PersonRepository;
import com.nextzakir.springdataneo4jrestapi.node.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*", exposedHeaders = {"X-Total-Count", "first", "last", "next", "prev"}, methods= {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PUT,RequestMethod.OPTIONS})
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public ResponseEntity<List<Person>> index(@PageableDefault(size = 15) Pageable pageable) {

        Page<Person> contents = personRepository.findAll(pageable);

        if (contents.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            long totalContents = contents.getTotalElements();
            int nbPageContents = contents.getNumberOfElements();


            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Total-Count", String.valueOf(totalContents));

            if (nbPageContents < totalContents) {
                headers.add("first", Helper.buildPageUri(PageRequest.of(0, contents.getSize())));
                headers.add("last", Helper.buildPageUri(PageRequest.of(contents.getTotalPages() - 1, contents.getSize())));

                if (contents.hasNext()) {
                    headers.add("next", Helper.buildPageUri(contents.nextPageable()));
                }

                if (contents.hasPrevious()) {
                    headers.add("prev", Helper.buildPageUri(contents.previousPageable()));
                }

                return new ResponseEntity<>(contents.getContent(), headers, HttpStatus.PARTIAL_CONTENT);
            } else {
                return new ResponseEntity<>(contents.getContent(), headers, HttpStatus.OK);
            }
        }

    }

    @PostMapping("/")
    public ResponseEntity<Person> store(@RequestParam("personId") Long personId,
                                        @RequestParam("personName") String personName) {

        Person person = new Person();
        person.setPersonId(personId);
        person.setPersonName(personName);
        person.setCreatedAt(new Date(Calendar.getInstance().getTimeInMillis()));
        person.setUpdatedAt(new Date(Calendar.getInstance().getTimeInMillis()));

        try {
            person = personRepository.save(person);
        } catch (Exception e) {
            logger.error("Could not save person information. ERROR: " + e.getMessage());
            throw new InternalServerErrorException("Something went wrong on the server");
        }

        return new ResponseEntity<>(person, HttpStatus.CREATED);

    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> show(@PathVariable("personId") Long personId) {

        return new ResponseEntity<>(
                personRepository.findByPersonId(personId)
                        .orElseThrow(() -> new NotFoundException("No person with this personId is found!")),
                HttpStatus.OK
        );

    }

    @PutMapping("/{personId}")
    public ResponseEntity<Person> update(@PathVariable("personId") Long personId,
                                         @RequestParam("personName") @Nullable String personName) {

        Person person = personRepository.findByPersonId(personId)
                .orElseThrow(() -> new NotFoundException("No person with this personId is found!"));

        if (personName != null && !personName.isBlank()) {
            person.setPersonName(personName);
            person.setUpdatedAt(new Date(Calendar.getInstance().getTimeInMillis()));

            try {
                person = personRepository.save(person);
            } catch (Exception e) {
                logger.error("Could not update person information. ERROR: " + e.getMessage());
                throw new InternalServerErrorException("Something went wrong on the server");
            }

            return new ResponseEntity<>(person, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(person, HttpStatus.OK);

    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Person> destroy(@PathVariable("personId") Long personId) {

        Person person = personRepository.findByPersonId(personId)
                .orElseThrow(() -> new NotFoundException("No person with this personId is found!"));

        try {
            personRepository.delete(person);
        } catch (Exception e) {
            logger.error("Could not delete person information. ERROR: " + e.getMessage());
            throw new InternalServerErrorException("Something went wrong on the server");
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}