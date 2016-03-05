package com.spring.hibernate.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Department {

    private int id;

    private String name;

    private College college;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public College getCollege() {
        return college;
    }

    @Override
    public boolean equals(Object o) {
        return new EqualsBuilder().reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).reflectionHashCode(this);
    }

}
