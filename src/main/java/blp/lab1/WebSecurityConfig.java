package blp.lab1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/food/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/order/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/order/add").hasAnyRole()
                .antMatchers(HttpMethod.POST, "/order/**/pay").hasAnyRole()
                .antMatchers(HttpMethod.POST, "/order/**/to_restaurant").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/order/**/to_cook").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/order/**/courier_assigned").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/restaurant/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "user/add").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/user/logout", "POST")) //защита CSRF
                .and()
                .httpBasic().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // управляет только тем, что делает SS, а не всем приложением. SS не может создать сеанс, если мы не даем ему указанияПо умолчанию SS создаст сеанс, когда ему нужно("ifRequired").
                .and()
                .csrf().disable();
    }

    //кодирование пароля
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
