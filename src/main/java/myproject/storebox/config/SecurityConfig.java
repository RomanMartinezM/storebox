package myproject.storebox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/health").permitAll()  // Allow public access to health endpoint
                .requestMatchers("/actuator/**").authenticated()   // Secure other actuator endpoints
                .requestMatchers("/api/auth/**").permitAll()      // Allow auth endpoints
                .anyRequest().authenticated()                      // Secure all other endpoints
            )
            .httpBasic(httpBasic -> httpBasic.realmName("StoreBox API"))
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
