package it.unisalento.pas.checking.repository;

import it.unisalento.pas.checking.domain.Rifiuto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RifiutoRepository extends MongoRepository<Rifiuto, String> {
    public List<Rifiuto> findByUsername(String username);
}
