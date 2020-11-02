package com.danbro.distributed.security.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Classname PermissionDto
 * @Description TODO
 * @Date 2020/11/2 11:00
 * @Author Danrbo
 */
@Data
@TableName(value = "t_permission")
public class PermissionDto {
    private Integer id;
    private String code;
    private String description;
    private String url;
}
