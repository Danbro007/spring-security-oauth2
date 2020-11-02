package com.danbro.distributed.security.uaa.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.danbro.distributed.security.uaa.entity.UserDto;
import com.danbro.distributed.security.uaa.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname SpringDataUserDetailsService
 * @Description TODO
 * @Date 2020/10/28 18:56
 * @Author Danrbo
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    @Autowired
    UserDtoMapper userDtoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserDto userDto = userDtoMapper.selectOne(queryWrapper);
        if (userDto == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        // 把查询到的权限转换成一个字符串数组
        List<String> permissions = new ArrayList<>();
        userDtoMapper.getPermissionByUserId(userDto.getId()).forEach(e->permissions.add(e.getCode()));
        String[] stringArray = new String[permissions.size()];
        permissions.toArray(stringArray);
        return User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities(stringArray).build();
    }
}
