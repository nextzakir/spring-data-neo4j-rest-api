package com.nextzakir.springdataneo4jrestapi.repository;

import com.nextzakir.springdataneo4jrestapi.node.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
}