package com.lsd.transaction.controller;

import com.lsd.transaction.entity.Stu;
import com.lsd.transaction.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lsd
 * 2020-08-31 11:41
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test1")
    public void test1() {
        testService.insert1(new Stu().setSname("明世隐").setAge(20));
    }

    @GetMapping("/test2")
    public void test2() {
        testService.insert2(new Stu().setSname("孙尚香").setAge(18));
    }

    @GetMapping("/test12")
    public void test12() {
        testService.insert12(
                new Stu().setSname("鲁班七号").setAge(28), new Stu().setSname("花木兰").setAge(17)
        );
    }

    @GetMapping("/test3")
    public void test3() {
        testService.insert3(new Stu().setSname("不知火舞").setAge(16));
    }

    @GetMapping("/test4")
    public void test4() {
        testService.insert7(new Stu().setSname("橘右京").setAge(11));
    }

}
