package it.unisalento.pas.pagare.configuration;

import it.unisalento.pas.pagare.security.JwtAuthenticationFilter;
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

@Configuration
@EnableMethodSecurity
public class SecurityConf {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    DefaultSecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .build();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests().requestMatchers("/cittadino/utenti/**").permitAll();

        httpSecurity.authorizeRequests().requestMatchers("/cittadino/pagamenti/**").hasRole("cittadino").and().
                addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeRequests().requestMatchers("/cittadino/tassa/mie").hasRole("cittadino").and().
                addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeRequests().requestMatchers("/cittadino/pagamenti/**").authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authorizeRequests().requestMatchers("/cittadino/tassa/mie").authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return httpSecurity.build();
    }
}
