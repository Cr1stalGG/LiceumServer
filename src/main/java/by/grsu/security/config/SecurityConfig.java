package by.grsu.security.config;

import by.grsu.entity.enumiration.Role;
import by.grsu.security.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests().requestMatchers("/sign/*").permitAll();
        http.authorizeHttpRequests().requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/accounts/*").hasAuthority(String.valueOf(Role.USER));
        http.authorizeHttpRequests().requestMatchers("/admins/*").hasAuthority(String.valueOf(Role.ADMIN));
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.logout().logoutUrl("/logout");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
