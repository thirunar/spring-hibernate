package com.spring.hibernate.repository;

import com.spring.hibernate.SpringHibernateApplication;
import com.spring.hibernate.builder.CollegeBuilder;
import com.spring.hibernate.builder.DepartmentBuilder;
import com.spring.hibernate.model.College;
import com.spring.hibernate.model.Department;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringHibernateApplication.class)
@Transactional
@Rollback
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Mock
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void saveShouldSaveTheDepartmentToDatabase() throws Exception {
        Department department = new DepartmentBuilder().withStubData().build();

        departmentRepository.save(department);

        Department departmentFromRepository = departmentRepository.getDepartment(department.getId());
        assertThat(departmentFromRepository, is(department));
    }

}