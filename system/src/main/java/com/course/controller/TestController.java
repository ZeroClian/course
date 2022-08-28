package com.course.controller;

import com.course.domain.Test;
import com.course.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Justin
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/test")
    public List<Test> test() {
        return testService.list();
    }
}
