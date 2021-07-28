package com.example.ABS.ITI.FirstProject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {
    @RequestMapping("/test")
    public String getTest(){
        return "helllow test !!";
    }
}
