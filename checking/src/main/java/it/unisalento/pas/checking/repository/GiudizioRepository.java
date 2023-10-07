package it.unisalento.pas.checking.repository;

import it.unisalento.pas.checking.domain.Giudizio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiudizioRepository extends MongoRepository<Giudizio, String> {

    public List<Giudizio> findByUsername(String username);
}
