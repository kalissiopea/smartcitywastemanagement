package it.unisalento.pas.emissione.repository;

import it.unisalento.pas.emissione.domain.Tassa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TassaRepository extends MongoRepository<Tassa, String> {
    public List<Tassa> findByUsername(String username);
}