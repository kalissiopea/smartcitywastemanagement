package it.unisalento.pas.checking.repository;

import it.unisalento.pas.checking.domain.Cassonetto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CassonettoRepository extends MongoRepository<Cassonetto, String> {

    public List<Cassonetto> findByStato(float stato);
    public int deleteByLuogo(String luogo);
}
