package com.spring.hibernate.repository;

import com.spring.hibernate.SpringHibernateApplication;
import com.spring.hibernate.builder.CollegeBuilder;
import com.spring.hibernate.builder.DepartmentBuilder;
import com.spring.hibernate.model.College;
import com.spring.hibernate.model.Department;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringHibernateApplication.class)
@Transactional
@Rollback
public class CollegeRepositoryTest {

    @Autowired
    private CollegeRepository collegeRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void saveShouldAddTheCollegeDetailsToTheDatabase() throws Exception {
        College college = new CollegeBuilder().withStubData().build();

        collegeRepository.save(college);

        College collegeFromRepository = collegeRepository.getCollege(college.getId());

        assertThat(collegeFromRepository.getName(), is("PSG Tech"));
    }

    @Test
    public void getDepartmentForTheCollegeShouldReturnTheListOfDepartmentsThatBelongToTheCollege() throws Exception {
        Department cse = new DepartmentBuilder().withStubData().setName("CSE").build();
        Department ece = new DepartmentBuilder().withStubData().setName("ECE").build();
        College college = new CollegeBuilder().withStubData().setDepartments(Arrays.asList(cse, ece)).build();
        collegeRepository.save(college);

        List<Department> departmentsFromRepository = collegeRepository.getDepartmentsForTheCollege(college.getId());

        assertThat(departmentsFromRepository.size(), is(2));
    }

}