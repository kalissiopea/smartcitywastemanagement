package it.unisalento.pas.login.repository;

import it.unisalento.pas.login.domain.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    public Cliente findByUsername(String username);
}
