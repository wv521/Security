package com.security.config;


import com.security.service.impl.MyAuthentiacationProvider;
import com.security.service.impl.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    //处理认证具体实现
    @Autowired
    private MyAuthentiacationProvider myAuthentiacationProvider;

    //成功处理器
    @Autowired
    private MyLoginSuccessHandler myLoginSuccessHandler;

    //失败处理器
    @Autowired
    private MyLoginFailureHandler myLoginFailureHandler;

    //获取用户信息实现
    @Autowired
    private MyUserService myUserService;

    //security异常处理类
    @Resource
    private AuthenticationEntryPoint myLoginEntryPoint;



    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
//        http.
//                addFilterBefore(myUsernamePasswordFilter(), UsernamePasswordAuthenticationFilter.class);
        http.
                authorizeRequests()
                .antMatchers("/hello").hasRole("ADMIN")
                .antMatchers("/","/logoutLogon","/loginPage","/errorPage").permitAll()
                .anyRequest().authenticated();
//        http.exceptionHandling().authenticationEntryPoint(myLoginEntryPoint);
        http.
                formLogin()
                .loginPage("/")  //自定义登录页请求地址
//                .loginProcessingUrl("/loginPage")  //自定义登录请求地址
//                .successHandler(myLoginSuccessHandler) //登录成功处理类
//                .failureHandler(myLoginFailureHandler) //登录失败处理类
//                .failureForwardUrl("/errorPage") //登录失败后请求路径
                .failureUrl("/errorPage")
                .defaultSuccessUrl("/index");  //登录成功后跳转地址
//                .permitAll();
//        http.addFilter(new JWTAuthenticationFilter(authenticationManagerBean()));
        http.
                logout()
                .logoutUrl("/logoutLogon") //自定义退出地址
                .logoutSuccessUrl("/"); //退出之后回到登录页面

        http.httpBasic().disable();
    }

    //将认证器注册到认证管理器中
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件.可以添加多个auth.authenticationProvider(..).authenticationProvider(..)..略
        auth.authenticationProvider(myAuthentiacationProvider);
}

    //构建认证管理器 AuthenticationManager
    @Bean
    public  AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 注入密码加密对象
     * @return Spring原生的加密对象Bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 将默认的认证中心中的属性 改为业务中的自定义的实现类  ---如果希望登录方法不经过控制层时使用(非前后台分离)
     * @return
     */
//    @Bean
//    DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(myUserService);
//        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        return daoAuthenticationProvider;
//    }


    //注册自定义的UsernamePasswordAuthenticationFilter ---如果希望登录方法不经过控制层时使用(非前后台分离)
//    @Bean
//    MyUsernamePasswordFilter myUsernamePasswordFilter() throws Exception {
//
//        MyUsernamePasswordFilter filter = new MyUsernamePasswordFilter();
//        filter.setFilterProcessesUrl("/loginPage");
//        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
//        filter.setAuthenticationManager(authenticationManagerBean());
//        filter.setAuthenticationSuccessHandler(myLoginSuccessHandler);
//        filter.setAuthenticationFailureHandler(myLoginFailureHandler);
//        return filter;
//    }


    /**
     * 祛除 ROLE前缀
     * @return
     */
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }
}


