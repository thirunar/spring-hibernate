package com.spring.hibernate.model;

public class Staff {

    private int id;

    private String name;

    private String designation;

    private Department department;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public Department getDepartment() {
        return department;
    }
}
