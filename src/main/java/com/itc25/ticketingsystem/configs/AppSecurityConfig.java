package com.itc25.ticketingsystem.configs;

import com.itc25.ticketingsystem.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public AppSecurityConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth").authenticated()
                .antMatchers("/auth/register").permitAll()
                .antMatchers("/auth/admin").hasRole("ADMIN")
                // employee
                .antMatchers("/employee").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/employee/insert").hasRole("ADMIN")
                .antMatchers("/employee/update/{employeeId}").hasRole("ADMIN")
                .antMatchers("/employee/delete/{employeeId}").hasRole("ADMIN")
                .antMatchers("/employee/technicalSupport").hasAnyRole("ADMIN", "MANAGER", "TECH_SUPPORT")
                // tiket
                .antMatchers("/ticket").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/ticket/insert").hasAnyRole("ADMIN")
                .antMatchers("/ticket/cancel").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/ticket/complete").hasRole("MANAGER")
                .antMatchers("/ticket/year").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/ticket/status").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/ticket/urgency").hasAnyRole("ADIMN", "MANAGER")

                .and()
                .httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
