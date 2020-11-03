package com.danbro.distributed.security.order.controller;

import com.danbro.distributed.security.order.entity.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PreAuthorize("hasAuthority('p1')")
    @GetMapping(value = "/r1")
    public String r1() {
        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername() + "访问资源1";
    }

    @PreAuthorize("hasAuthority('p2')")
    @GetMapping(value = "/r2")
    public String r2() {//通过Spring Security API获取当前登录用户
        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername() + "访问资源2";
    }
}
