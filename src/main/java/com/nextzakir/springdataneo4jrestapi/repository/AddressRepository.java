package com.nextzakir.springdataneo4jrestapi.repository;

import com.nextzakir.springdataneo4jrestapi.node.Address;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends Neo4jRepository<Address, Long> {
}