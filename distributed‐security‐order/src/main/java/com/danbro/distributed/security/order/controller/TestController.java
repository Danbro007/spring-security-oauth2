package com.danbro.distributed.security.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestControleer
 * @Description TODO
 * @Date 2020/11/2 15:34
 * @Author Danrbo
 */
@RestController
public class TestController {

    @GetMapping("/r1")
    @PreAuthorize("hasAuthority('p1')")
    public String r1(){
        return "访问资源1";
    }
}
