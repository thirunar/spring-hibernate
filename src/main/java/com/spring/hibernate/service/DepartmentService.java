package com.spring.hibernate.service;

import com.spring.hibernate.model.College;
import com.spring.hibernate.model.Department;
import com.spring.hibernate.repository.CollegeRepository;
import com.spring.hibernate.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department getDepartment(long id) {
        return departmentRepository.getDepartment(id);
    }


    public void save(Department department) {
        departmentRepository.save(department);
    }
}
