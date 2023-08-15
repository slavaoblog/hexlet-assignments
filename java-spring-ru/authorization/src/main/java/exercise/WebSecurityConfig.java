package exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import exercise.model.UserRole;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    final UserDetailsServiceImpl userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // BEGIN
        http.csrf().disable()
                .authorizeHttpRequests((request) -> request
                            .requestMatchers("/").permitAll()
                            .requestMatchers(POST,"/users/**").permitAll()
                            .requestMatchers(GET,"users/**").hasAnyAuthority(UserRole.USER.name(), UserRole.ADMIN.name())
                            .requestMatchers(DELETE).hasAuthority(UserRole.ADMIN.name())
                            .anyRequest().authenticated()
                )
                .httpBasic();
        return http.build();
        // END
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
