package com.spring.hibernate.controller;

import com.spring.hibernate.model.College;
import com.spring.hibernate.model.Department;
import com.spring.hibernate.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Department getDepartment(@RequestParam long id) {
        return departmentService.getDepartment(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "text/plain", consumes = "application/json")
    public String save(@RequestBody Department department) {
        departmentService.save(department);
        return "Successfully saved";
    }
}
