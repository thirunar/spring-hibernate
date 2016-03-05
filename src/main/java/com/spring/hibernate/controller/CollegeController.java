package com.spring.hibernate.controller;

import com.spring.hibernate.model.College;
import com.spring.hibernate.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public College getCollege(@RequestParam long id) {
        return collegeService.getCollege(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "text/plain", consumes = "application/json")
    public String save(@RequestBody College college) {
        collegeService.save(college);
        return "Successfully saved";
    }
}
