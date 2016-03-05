package com.spring.hibernate.service;

import com.spring.hibernate.builder.DepartmentBuilder;
import com.spring.hibernate.model.Department;
import com.spring.hibernate.repository.DepartmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getDepartmentShouldGetTheDepartmentBasedOnTheId() throws Exception {
        Department department = new DepartmentBuilder().withStubData().build();
        when(departmentRepository.getDepartment(0)).thenReturn(department);

        Department actualDepartment = departmentService.getDepartment(0);

        assertThat(actualDepartment, is(department));
        verify(departmentRepository).getDepartment(0);
    }

    @Test
    public void saveShouldSaveTheDepartmentToTheDatabase() throws Exception {
        Department department = new DepartmentBuilder().withStubData().build();

        departmentService.save(department);

        verify(departmentRepository).save(department);
    }
}