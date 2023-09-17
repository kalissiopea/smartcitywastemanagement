package it.unisalento.pas.monitoraggio.repository;

import it.unisalento.pas.monitoraggio.domain.Percorso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PercorsoRepository extends MongoRepository<Percorso, String> {

    List<Percorso> findByDate(String date);

    int deleteByDate(String date);
}
