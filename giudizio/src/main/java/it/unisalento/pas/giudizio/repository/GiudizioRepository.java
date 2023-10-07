package it.unisalento.pas.giudizio.repository;

import it.unisalento.pas.giudizio.domain.Giudizio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiudizioRepository extends MongoRepository<Giudizio, String> {

}
