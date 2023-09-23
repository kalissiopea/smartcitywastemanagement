package it.unisalento.pas.emissione.repository;

import it.unisalento.pas.emissione.domain.Checking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckingRepository extends MongoRepository<Checking, String> {

    public Checking findByUsername(String username);
}
