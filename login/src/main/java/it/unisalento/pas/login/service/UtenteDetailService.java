package it.unisalento.pas.login.service;

import it.unisalento.pas.login.domain.Cliente;
import it.unisalento.pas.login.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtenteDetailService implements UserDetailsService {
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Cliente cliente = clienteRepository.findByUsername(username);

        if(cliente == null){
            throw new UsernameNotFoundException(username);
        }

        UserDetails userDetails = User.withUsername(cliente.getUsername()).password(cliente.getPassword()).roles(cliente.getRuolo()).build();

        return userDetails;
    }

}
