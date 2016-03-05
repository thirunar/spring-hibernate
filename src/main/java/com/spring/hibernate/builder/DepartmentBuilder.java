package com.spring.hibernate.builder;

import com.spring.hibernate.model.College;
import com.spring.hibernate.model.Department;

public class DepartmentBuilder {

    private String name;

    public DepartmentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DepartmentBuilder withStubData() {
        setName("Computer Science");
//        setCollege(new CollegeBuilder().withStubData().build());
        return this;
    }

    public Department build() {
        return new Department(name);
    }
}