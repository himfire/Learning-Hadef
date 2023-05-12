package com.learning.hadef.config;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import static com.learning.hadef.domain.value.ApplicationUserRole.*;
//
//@Configuration
//@EnableWebSecurity
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
////                .authorizeRequests()
////                .antMatchers("/","/h2-console/**", "/css/*", "/js/*").permitAll()
//////                .antMatchers("/api/**").hasRole(STUDENT.name())
////                .anyRequest()
////                .authenticated()
////                .and()
////                .httpBasic();
//
//    }
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails hishamUser = User.builder().username("hisham").password(passwordEncoder.encode("test")).roles(EMPLOYEE.name()).build();
//        UserDetails khalidUser = User.builder().username("khaled").password(passwordEncoder.encode("test")).roles(STUDENT.name()).build();
//        UserDetails omarUser = User.builder().username("omar").password(passwordEncoder.encode("test")).roles(EMPLOYEE.name()).build();
//        UserDetails alaaUser = User.builder().username("alaa").password(passwordEncoder.encode("test")).roles(ADMIN.name()).build();
//        return new InMemoryUserDetailsManager(hishamUser,khalidUser,omarUser,alaaUser);
//    }
//}
