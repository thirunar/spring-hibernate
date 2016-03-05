package com.spring.hibernate.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COLLEGE")
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "college", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Department> departments;

    public College() {
    }

    public College(int id) {
        this.id = id;
    }

    public College(String name, List<Department> departments) {
        this.name = name;
        this.departments = departments;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        return new EqualsBuilder().reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).reflectionHashCode(this);
    }

    public List<Department> getDepartments() {
        return departments;
    }
}
