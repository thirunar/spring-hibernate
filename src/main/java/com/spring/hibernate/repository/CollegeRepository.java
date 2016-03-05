package com.spring.hibernate.repository;

import com.spring.hibernate.model.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository
public class CollegeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Transactional
    public void save(College college) {
        entityManager.persist(college);
    }

    public College getCollege(int id) {
        return entityManager.find(College.class, id);
    }
}
