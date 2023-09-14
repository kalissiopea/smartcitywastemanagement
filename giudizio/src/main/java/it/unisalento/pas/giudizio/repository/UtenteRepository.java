package it.unisalento.pas.giudizio.repository;

import it.unisalento.pas.giudizio.domain.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UtenteRepository extends MongoRepository<Utente, String> {
    public List<Utente> findByRuolo(String ruolo);
    public Utente findByUsername(String username);

    public int deleteByUsername(String username);
}
