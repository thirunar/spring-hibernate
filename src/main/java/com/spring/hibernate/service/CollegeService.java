package com.spring.hibernate.service;

import com.spring.hibernate.model.College;
import com.spring.hibernate.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    public College getCollege(long id) {
        return collegeRepository.getCollege(id);
    }


    public void save(College college) {
        collegeRepository.save(college);
    }
}
