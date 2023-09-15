package it.unisalento.pas.checking.repository;

import it.unisalento.pas.checking.domain.Giudizio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GiudizioRepository extends MongoRepository<Giudizio, String> {

    public List<Giudizio> findByUsername(String username);
}
