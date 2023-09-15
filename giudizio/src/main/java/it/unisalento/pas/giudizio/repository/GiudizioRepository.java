package it.unisalento.pas.giudizio.repository;

import it.unisalento.pas.giudizio.domain.Giudizio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiudizioRepository extends MongoRepository<Giudizio, String> {

}
