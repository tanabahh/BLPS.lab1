package blp.lab1;

import blp.lab1.service.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UsersDetailsService usersDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //.authorizeRequests().anyRequest().authenticated().and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/food/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/order/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/order/add").authenticated()
                .antMatchers(HttpMethod.POST, "/order/**/pay").authenticated()
                .antMatchers(HttpMethod.POST, "/order/**/to_restaurant").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/order/**/to_cook").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/order/**/courier_assigned").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/restaurant/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/user/add").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and().sessionManagement().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("ADMIN");
        builder.userDetailsService(usersDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}