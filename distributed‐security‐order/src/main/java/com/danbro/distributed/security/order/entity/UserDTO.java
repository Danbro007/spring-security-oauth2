package com.danbro.distributed.security.order.entity;

import lombok.Data;

/**
 * @Classname UserDTO
 * @Description TODO
 * @Date 2020/11/3 11:49
 * @Author Danrbo
 */
@Data
public class UserDTO {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 姓名
     */
    private String fullname;

}
