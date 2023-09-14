package it.unisalento.pas.checking.repository;

import it.unisalento.pas.checking.domain.Cassonetto;
import it.unisalento.pas.checking.domain.Checking;
import it.unisalento.pas.checking.domain.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckingRepository extends MongoRepository<Checking, String> {

    public Checking findByUsername(String username);
}
