package com.spring.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COLLEGE")
public class College {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    public College() {
    }

    public College(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
