package it.unisalento.pas.simulazione.repository;

import it.unisalento.pas.simulazione.domain.Cassonetto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CassonettoRepository extends MongoRepository<Cassonetto, String> {

    public List<Cassonetto> findByLuogo(float luogo);
    public int deleteByLuogo(String luogo);
}
