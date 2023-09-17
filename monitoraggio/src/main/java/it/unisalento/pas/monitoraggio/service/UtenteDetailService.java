package it.unisalento.pas.monitoraggio.service;

import it.unisalento.pas.monitoraggio.domain.Utente;
import it.unisalento.pas.monitoraggio.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtenteDetailService implements UserDetailsService {
    @Autowired
    UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Utente utente = utenteRepository.findByUsername(username);

        if(utente == null){
            throw new UsernameNotFoundException(username);
        }

        UserDetails userDetails = User.withUsername(utente.getUsername()).password(utente.getPassword()).roles(utente.getRuolo()).build();

        return userDetails;
    }
}
