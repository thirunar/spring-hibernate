package com.spring.hibernate.builder;

import com.spring.hibernate.model.College;
import com.spring.hibernate.model.Department;

import java.util.Arrays;
import java.util.List;

public class CollegeBuilder {

    private String name;
    private List<Department> departments;

    public CollegeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CollegeBuilder setDepartments(List<Department> departments) {
        this.departments = departments;
        return this;
    }

    public CollegeBuilder withStubData() {
        setName("PSG Tech");
        setDepartments(Arrays.asList(new DepartmentBuilder().withStubData().build()));
        return this;
    }

    public College build() {
        return new College(name, departments);
    }

}