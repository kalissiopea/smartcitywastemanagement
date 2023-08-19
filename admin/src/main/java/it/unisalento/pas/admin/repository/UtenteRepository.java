package it.unisalento.pas.admin.repository;

import it.unisalento.pas.admin.domain.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//interfaccia su come trovare utenti registrati dall'admin
public interface UtenteRepository extends MongoRepository<Utente, String> {
    public List<Utente> findByRuolo(String ruolo);
    public Utente findByUsername(String username);

    public int deleteByUsername(String username);
}
