package com.danbro.distributed.security.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname UserDto
 * @Description TODO
 * @Date 2020/10/27 11:38
 * @Author Danrbo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
public class UserDto {
    private Integer id;
    private String username;
    private String password;
}
