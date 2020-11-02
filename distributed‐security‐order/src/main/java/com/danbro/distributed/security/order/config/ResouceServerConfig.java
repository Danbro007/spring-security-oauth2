package com.danbro.distributed.security.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Classname ResouceServerConfig
 * @Description TODO
 * @Date 2020/11/2 15:23
 * @Author Danrbo
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "res1";

    @Autowired
    TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(RESOURCE_ID)
                .tokenStore(tokenStore)
                .stateless(true);

    }

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources
//                .resourceId(RESOURCE_ID)// 资源ID
//                .tokenServices(getTokenService())// 验证令牌服务
//                .stateless(true);
//
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and().csrf().disable()// 关闭 csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// 关闭 session
    }

//    @Bean
//    public ResourceServerTokenServices getTokenService(){
//        // 使用远程服务请求授权服务器校验 token 的 url、client_id、client_secret。
//        RemoteTokenServices tokenServices = new RemoteTokenServices();
//        // 请求这个地址来校验令牌
//        tokenServices.setCheckTokenEndpointUrl("http://localhost:53020/uaa/oauth/check_token");
//        tokenServices.setClientId("c1");
//        tokenServices.setClientSecret("secret");
//        return tokenServices;
//    }

}
