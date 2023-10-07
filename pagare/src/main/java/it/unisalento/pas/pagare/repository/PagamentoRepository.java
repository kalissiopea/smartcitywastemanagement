package it.unisalento.pas.pagare.repository;

import it.unisalento.pas.pagare.domain.Pagamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends MongoRepository<Pagamento,String> {

    public List<Pagamento> findByStato(Boolean stato);

    public List<Pagamento> findByUsername(String username);

}

