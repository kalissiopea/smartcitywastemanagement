package it.unisalento.pas.checking.repository;

import it.unisalento.pas.checking.domain.Checking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckingRepository extends MongoRepository<Checking, String> {

}
