package it.unisalento.pas.admin.configuration;

import it.unisalento.pas.admin.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    DefaultSecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .build();
    }

    @Bean
    //@Order(2)
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .authorizeRequests().requestMatchers(HttpMethod.OPTIONS, "/admin/**").permitAll().and()
//                .authorizeRequests().requestMatchers("/admin/utenti/aggiungi").permitAll().and()
//                .authorizeRequests().requestMatchers("/admin/utenti/lista").permitAll().and()
//                .authorizeRequests().requestMatchers("/admin/cassonetti/lista").permitAll().and()
//                .authorizeRequests().requestMatchers("/admin/cassonetti/aggiornaStato/{luogo}/{stato}").permitAll().and()
//                .authorizeRequests().requestMatchers(HttpMethod.POST, "/admin/utenti/registra").hasRole("amministratore").and()
//                .authorizeRequests().requestMatchers("/admin/cassonetti/aggiungi").hasRole("amministratore")
//                .and().authorizeRequests().requestMatchers("/admin/**").authenticated()
//                .and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        return httpSecurity.build();
        httpSecurity.csrf().disable()
                .authorizeRequests().requestMatchers(HttpMethod.OPTIONS, "/admin/**").permitAll().and()
                .authorizeRequests().requestMatchers("/admin/utenti/registra").hasRole("amministratore").and()
                .authorizeRequests().requestMatchers("/admin/cassonetti/aggiungi").hasRole("amministratore")
                .anyRequest()
                .authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}



