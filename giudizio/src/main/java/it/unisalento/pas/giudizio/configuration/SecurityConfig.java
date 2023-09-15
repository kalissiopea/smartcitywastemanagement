package it.unisalento.pas.giudizio.configuration;

import it.unisalento.pas.giudizio.security.JwtAuthenticationFilter;
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
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

/*    @Bean
    DefaultSecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .build();
    }*/

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests().requestMatchers("/giudizio/check/**").permitAll().
                and().authorizeRequests().requestMatchers("/giudizio/utenti/**").permitAll();

        httpSecurity.authorizeRequests().requestMatchers("/giudizio/consulta/**").hasRole("impiegato").and().
                addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeRequests().requestMatchers("/giudizio/check/lista").hasRole("impiegato").and().
                addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeRequests().requestMatchers("/giudizio/consulta/**").authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authorizeRequests().requestMatchers("/giudizio/check/lista").authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return httpSecurity.build();
    }

}
