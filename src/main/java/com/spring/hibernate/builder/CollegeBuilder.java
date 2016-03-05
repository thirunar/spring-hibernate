package com.spring.hibernate.builder;

import com.spring.hibernate.model.College;

public class CollegeBuilder {

    private String name;

    public CollegeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CollegeBuilder withStubData() {
        setName("PSG Tech");
        return this;
    }

    public College build() {
        return new College(name);
    }
}