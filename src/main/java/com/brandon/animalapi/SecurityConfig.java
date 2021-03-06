package com.brandon.animalapi;

import com.brandon.animalapi.security.JWTFilter;
import com.brandon.animalapi.security.JWTProvider;
import com.brandon.animalapi.security.UnauthenticatedHandler;
import com.brandon.animalapi.security.UserAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JWTProvider jwtProvider;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    public JWTProvider jwtProvider(UserDetailsService userDetailsService, @Value("secret.key") String secretKey) {
        return new JWTProvider(userDetailsService, secretKey);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        JdbcUserDetailsManager userDetailsService = auth.jdbcAuthentication()
                .dataSource(dataSource)
                .getUserDetailsService();

        // Spring requires USERNAME, PASSWORD and ENABLED
        userDetailsService
                .setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");

        // Here Spring requires USERNAME and ROLE
        userDetailsService
                .setAuthoritiesByUsernameQuery("SELECT username, role FROM users WHERE username = ?");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        enableRESTAuthentication(http)
                .authorizeRequests()
                .anyRequest()
                .hasRole("USER")
                .and()
                .csrf()
                .disable();
    }

    private HttpSecurity enableRESTAuthentication(HttpSecurity http) throws Exception {
                http
                .authorizeRequests()
                .mvcMatchers("/authenticate")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new UserAccessDeniedHandler())
                .authenticationEntryPoint(new UnauthenticatedHandler());
        http.addFilterBefore(new JWTFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http;
    }
}
