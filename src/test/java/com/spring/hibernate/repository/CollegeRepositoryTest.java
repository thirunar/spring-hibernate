package com.spring.hibernate.repository;

import com.spring.hibernate.SpringHibernateApplication;
import com.spring.hibernate.builder.CollegeBuilder;
import com.spring.hibernate.model.College;
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
}