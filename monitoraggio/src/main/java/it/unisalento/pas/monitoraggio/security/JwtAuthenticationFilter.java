package it.unisalento.pas.monitoraggio.security;

import it.unisalento.pas.monitoraggio.service.UtenteDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtilities jwtUtilities ;

    @Autowired
    private UtenteDetailService customerUserDetailsService ;

    String username = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        //String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtilities.extractUsername(jwt);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.customerUserDetailsService.loadUserByUsername(username);

            if (jwtUtilities.validateToken(jwt, userDetails)) {

                Set<GrantedAuthority> authorities = new HashSet<>();
                userDetails.getAuthorities().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
                });


                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, /*userDetails.getAuthorities()*/ authorities);
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // Aggiungi il token come parametro nella richiesta URL
        if (jwt != null) {
            String modifiedUrl = request.getRequestURI() + "?token=" + jwt + "&username=" + username;
            RequestDispatcher dispatcher = request.getRequestDispatcher(modifiedUrl);
            dispatcher.forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }
}
