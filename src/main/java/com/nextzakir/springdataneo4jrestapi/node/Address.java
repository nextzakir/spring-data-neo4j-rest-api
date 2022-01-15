package com.nextzakir.springdataneo4jrestapi.node;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Node
@Validated
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long addressId;

    @NotNull
    private String addressLineOne;

    @Nullable
    private String addressLineTwo;

    @NotNull
    private Date createdAt;

    @NotNull
    private Date updatedAt;

    public Address() {
    }

    public Address(Long addressId, String addressLineOne, @Nullable String addressLineTwo, Date createdAt, Date updatedAt) {
        this.addressId = addressId;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Address(Long id, Long addressId, String addressLineOne, @Nullable String addressLineTwo, Date createdAt, Date updatedAt) {
        this.id = id;
        this.addressId = addressId;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    @Nullable
    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(@Nullable String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
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
        Address address = (Address) o;
        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", addressId=" + addressId +
                ", addressLineOne='" + addressLineOne + '\'' +
                ", addressLineTwo='" + addressLineTwo + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}