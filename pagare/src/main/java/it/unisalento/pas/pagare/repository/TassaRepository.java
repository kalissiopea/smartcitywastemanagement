package it.unisalento.pas.pagare.repository;

import it.unisalento.pas.pagare.domain.Tassa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TassaRepository extends MongoRepository<Tassa,String> {

    public List<Tassa> findByUsername(String username);

}
