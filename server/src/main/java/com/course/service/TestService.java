package com.course.service;

import com.course.domain.Test;
import com.course.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZeroClian
 * @date 2022-08-27 4:12 下午
 */
@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.selectByExample(null);
    }
}
