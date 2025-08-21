package myproject.storebox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                .anyRequest().permitAll()                         // Allow all other requests
            )
            .httpBasic()                                          // Enable basic authentication
            .and()
            .csrf().disable();                                    // Disable CSRF for simplicity in development

        return http.build();
    }
}
