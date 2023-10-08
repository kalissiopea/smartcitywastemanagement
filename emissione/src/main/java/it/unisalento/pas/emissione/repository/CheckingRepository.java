package it.unisalento.pas.emissione.repository;

import it.unisalento.pas.emissione.domain.Checking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends MongoRepository<Checking, String> {

    public Checking findByUsername(String username);
    int deleteByUsername(String username);
}
