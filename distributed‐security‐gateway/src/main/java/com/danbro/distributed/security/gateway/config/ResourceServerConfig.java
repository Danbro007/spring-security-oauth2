package com.danbro.distributed.security.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Classname ResouceServerConfig
 * @Description TODO
 * @Date 2020/11/3 10:56
 * @Author Danrbo
 */
@Configuration
public class ResourceServerConfig {
    public static final String RESOURCE_ID = "res1";

    @Autowired
    TokenStore tokenStore;

    // UAA 服务
    @Configuration
    @EnableResourceServer
    public class UaaServerConfig extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources
                    .resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/uaa/**").permitAll();// 对 /uaa/** 的请求不做权限验证
        }
    }

    // Order 服务
    @Configuration
    @EnableResourceServer
    public class OrderServerConfig extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources
                    .resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    .stateless(true);
        }
        // 只有客户端的 scope 包含 ROLE_ADMIN 才能访问 /order/**
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().
                    antMatchers("/order/**").access("#oauth2.hasAnyScope('ROLE_API')");
        }
    }

}
