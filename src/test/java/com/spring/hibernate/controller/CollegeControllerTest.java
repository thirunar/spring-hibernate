package com.spring.hibernate.controller;

import com.spring.hibernate.builder.CollegeBuilder;
import com.spring.hibernate.model.College;
import com.spring.hibernate.service.CollegeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CollegeControllerTest {

    @InjectMocks
    private CollegeController collegeController;

    @Mock
    private CollegeService collegeService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getCollegesShouldReturnTheColleges() throws Exception {
        College college = new CollegeBuilder().withStubData().build();
        when(collegeService.getCollege(0)).thenReturn(college);

        College actualCollege = collegeController.getCollege(0);

        assertThat(actualCollege, is(college));
    }

    @Test
    public void saveShouldSaveTheCollege() throws Exception {
        College college = new CollegeBuilder().withStubData().build();

        collegeController.save(college);

        verify(collegeService).save(college);
    }
}