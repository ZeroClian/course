package com.course.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Justin
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "success";
    }
}
