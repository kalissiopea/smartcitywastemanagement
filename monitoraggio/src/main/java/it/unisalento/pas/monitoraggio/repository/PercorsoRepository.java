package it.unisalento.pas.monitoraggio.repository;

import it.unisalento.pas.monitoraggio.domain.Percorso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PercorsoRepository extends MongoRepository<Percorso, String> {

    List<Percorso> findByDate(String date);

    int deleteByDate(String date);
}
