package com.querydsl.chapternineteen.model;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    @Getter
    private String street, zipCode, city, state;

    protected Address() {

    }

    public Address(String street, String zipCode, String city, String state) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("%s, %s %s, %s", street, zipCode, city, state);
    }
}
