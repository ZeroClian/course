package com.course.business.controller;

import com.course.server.domain.Test;
import com.course.server.service.TestService;
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
