package it.unisalento.pas.checking.repository;

import it.unisalento.pas.checking.domain.Cassonetto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CassonettoRepository extends MongoRepository<Cassonetto, String> {

    public List<Cassonetto> findByStato(float stato);
    public Cassonetto findByLuogo(String luogo);
    public int deleteByLuogo(String luogo);
}
