package it.unisalento.pas.monitoraggio.repository;

import it.unisalento.pas.monitoraggio.domain.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//interfaccia su come trovare utenti registrati dall'admin
@Repository
public interface UtenteRepository extends MongoRepository<Utente, String> {
    public List<Utente> findByRuolo(String ruolo);
    public Utente findByUsername(String username);

    public int deleteByUsername(String username);
}
