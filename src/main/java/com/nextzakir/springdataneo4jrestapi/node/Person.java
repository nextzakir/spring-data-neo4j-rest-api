package com.nextzakir.springdataneo4jrestapi.node;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Node
@Validated
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long personId;

    @NotNull
    private String personName;

    @Nullable
    @Relationship(type = "KNOWS", direction = Relationship.Direction.INCOMING)
    private Person knownPerson;

    @Nullable
    @Relationship(type = "TEAM_MATE", direction = Relationship.Direction.INCOMING)
    private Person teamMate;

    @NotNull
    private Date createdAt;

    @NotNull
    private Date updatedAt;

    public Person() {
    }

    public Person(Long personId, String personName, Date createdAt, Date updatedAt) {
        this.personId = personId;
        this.personName = personName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Person(Long id, Long personId, String personName, @Nullable Person knownPerson, @Nullable Person teamMate, Date createdAt, Date updatedAt) {
        this.id = id;
        this.personId = personId;
        this.personName = personName;
        this.knownPerson = knownPerson;
        this.teamMate = teamMate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Nullable
    public Person getKnownPerson() {
        return knownPerson;
    }

    public void setKnownPerson(@Nullable Person knownPerson) {
        this.knownPerson = knownPerson;
    }

    @Nullable
    public Person getTeamMate() {
        return teamMate;
    }

    public void setTeamMate(@Nullable Person teamMate) {
        this.teamMate = teamMate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", personId=" + personId +
                ", personName='" + personName + '\'' +
                ", knownPerson=" + knownPerson +
                ", teamMate=" + teamMate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}