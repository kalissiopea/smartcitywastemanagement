package it.unisalento.pas.monitoraggio.repository;


import it.unisalento.pas.monitoraggio.domain.Cassonetto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CassonettoRepository extends MongoRepository<Cassonetto, String> {

    public List<Cassonetto> findByStato(float stato);
    public Cassonetto findByLuogo (String luogo);
    public int deleteByLuogo(String luogo);
}
