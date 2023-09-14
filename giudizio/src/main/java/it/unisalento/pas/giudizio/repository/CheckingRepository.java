package it.unisalento.pas.giudizio.repository;

import it.unisalento.pas.giudizio.domain.Checking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckingRepository extends MongoRepository<Checking, String> {

    public Checking findByUsername(String username);
}
