package it.unisalento.pas.emissione.repository;

import it.unisalento.pas.emissione.domain.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UtenteRepository extends MongoRepository<Utente,String> {
    Utente findByUsername(String username);
    int deleteByUsername(String username);

}
