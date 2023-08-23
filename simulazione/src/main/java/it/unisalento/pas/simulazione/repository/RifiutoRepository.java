package it.unisalento.pas.simulazione.repository;

import it.unisalento.pas.simulazione.domain.Rifiuto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RifiutoRepository extends MongoRepository<Rifiuto, String> {

}
