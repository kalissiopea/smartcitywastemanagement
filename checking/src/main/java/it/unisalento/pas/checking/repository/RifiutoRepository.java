package it.unisalento.pas.checking.repository;

import it.unisalento.pas.checking.domain.Rifiuto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RifiutoRepository extends MongoRepository<Rifiuto, String> {
    public List<Rifiuto> findByUsername(String username);
}
