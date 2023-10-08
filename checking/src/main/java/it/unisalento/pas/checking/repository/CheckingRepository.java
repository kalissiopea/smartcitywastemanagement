package it.unisalento.pas.checking.repository;

import it.unisalento.pas.checking.domain.Cassonetto;
import it.unisalento.pas.checking.domain.Checking;
import it.unisalento.pas.checking.domain.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends MongoRepository<Checking, String> {

    public Checking findByUsername(String username);
    int deleteByUsername(String username);
}
