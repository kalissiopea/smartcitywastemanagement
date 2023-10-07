package it.unisalento.pas.monitoraggio.repository;

import it.unisalento.pas.monitoraggio.domain.Allarme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllarmeRepository extends MongoRepository<Allarme, String> {
    List<Allarme> findByDate(String date);
    int deleteByDate(String date);
}
