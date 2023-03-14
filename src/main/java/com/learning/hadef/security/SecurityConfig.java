package com.learning.hadef.security;

import com.learning.hadef.model.value.ApplicationUserRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.learning.hadef.model.value.ApplicationUserRoles.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //TODO: This needs to be added later
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers("/student/api/v1/**").hasAnyRole(Student.name())
                .antMatchers("/teacher/api/v1/**").hasAnyRole(Teacher.name())
                .antMatchers("/admin/api/v1/**").hasAnyRole(Admin.name(),SuperAdmin.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
