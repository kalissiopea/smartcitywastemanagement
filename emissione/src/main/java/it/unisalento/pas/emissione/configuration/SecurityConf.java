package it.unisalento.pas.emissione.configuration;

import it.unisalento.pas.emissione.security.JwtAuthenticationFilter;
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
public class SecurityConf {

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
        httpSecurity.csrf().disable().authorizeRequests().requestMatchers("/cittadino/utenti/**").permitAll()
                        .and().authorizeRequests().requestMatchers("cittadino/check/**").permitAll();

        httpSecurity.authorizeRequests().requestMatchers("/cittadino/tassa/**").hasRole("impiegato").and().
                addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeRequests().requestMatchers("/cittadino/tassa/**").authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return httpSecurity.build();
    }
}
