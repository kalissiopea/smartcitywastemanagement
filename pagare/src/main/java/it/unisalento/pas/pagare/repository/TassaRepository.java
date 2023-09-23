package it.unisalento.pas.pagare.repository;

import it.unisalento.pas.pagare.domain.Tassa;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TassaRepository extends MongoRepository<Tassa,String> {

    public List<Tassa> findByUsername(String username);

}
