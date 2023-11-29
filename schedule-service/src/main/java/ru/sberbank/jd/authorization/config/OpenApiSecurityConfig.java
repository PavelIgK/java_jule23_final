package ru.sberbank.jd.authorization.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class OpenApiSecurityConfig {

    final DataSource dataSource;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @SneakyThrows
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth){
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(" select login, password, enabled "
                        + " from schedule_schema.auth_user "
                        + " where login = ? ")
                .authoritiesByUsernameQuery("select schedule_schema.auth_user.login, schedule_schema.authority.authority "
                        + " from schedule_schema.auth_user "
                        + " join schedule_schema.authority on schedule_schema.auth_user.id = schedule_schema.authority.user_id"
                        + " where schedule_schema.auth_user.login = ? ");
    }

    @SneakyThrows
    @Bean
    SecurityFilterChain adminFilterChain(HttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**"))
                        .hasAuthority("ADMIN")
                        .anyRequest()
                        .permitAll())
                .logout(logoutConfigurer -> logoutConfigurer
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(loginConfigurer -> loginConfigurer
                        .defaultSuccessUrl("/docs"))
                .exceptionHandling(exConfigurer -> exConfigurer
                        .accessDeniedPage("/access-denied"));
        return http.build();
    }

}

