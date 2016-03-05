package com.spring.hibernate.service;

import com.spring.hibernate.builder.CollegeBuilder;
import com.spring.hibernate.model.College;
import com.spring.hibernate.repository.CollegeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CollegeServiceTest {

    @InjectMocks
    private CollegeService collegeService;

    @Mock
    private CollegeRepository collegeRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getCollegeShouldReturnCollege() throws Exception {
        College college = new CollegeBuilder().withStubData().build();
        when(collegeRepository.getCollege(0)).thenReturn(college);

        College collegeFromService = collegeService.getCollege(0);

        Assert.assertThat(collegeFromService.getName(), is(college.getName()));
    }

    @Test
    public void saveShouldSaveTheCollege() throws Exception {
        College college = new CollegeBuilder().withStubData().build();

        collegeService.save(college);

        verify(collegeRepository).save(college);
    }
}