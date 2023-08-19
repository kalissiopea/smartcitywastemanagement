package it.unisalento.pas.admin.repository;

import it.unisalento.pas.admin.domain.Cassonetto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CassonettoRepository extends MongoRepository<Cassonetto, String> {

    public List<Cassonetto> findByStato(float stato);
    public int deleteByLuogo(String luogo);
}
