package it.unisalento.pas.checking.configuration;

import it.unisalento.pas.checking.security.JwtAuthenticationFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableMethodSecurity
@EnableAutoConfiguration
public class SecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

/*    @Bean
    DefaultSecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .build();
    }*/

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests().requestMatchers("/check/performance/**").permitAll().
                and().authorizeRequests().requestMatchers("/check/cassonetti/**").permitAll().
                and().authorizeRequests().requestMatchers("/check/rifiuti/**").permitAll().
                and().authorizeRequests().requestMatchers("/check/giudizio/**").permitAll().
                and().authorizeRequests().requestMatchers("/check/utenti/**").permitAll();

        httpSecurity.authorizeRequests().requestMatchers("/check/performance/mie").hasRole("cittadino").and().
                addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeRequests().requestMatchers("/check/giudizio/mie").hasRole("cittadino").and().
                addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeRequests().requestMatchers("/check/giudizio/mie").authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authorizeRequests().requestMatchers("/check/performance/mie").authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return httpSecurity.build();
    }
}
