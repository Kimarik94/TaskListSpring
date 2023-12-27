package ru.imp.TaskListSpring.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csfr -> csfr.disable())
            .cors(cors -> cors.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/","/auth/*").permitAll()
                        .requestMatchers("/admin/users").hasRole("ADMIN")
                        .requestMatchers("/user/alltasks").hasAnyRole()
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("auth/login").permitAll()
                        .defaultSuccessUrl("/user/home",true)
                        .failureUrl("auth/login?error"))
                .logout(logout -> logout
                        .logoutUrl("auth/logout")
                        .logoutSuccessUrl("auth/login"))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
