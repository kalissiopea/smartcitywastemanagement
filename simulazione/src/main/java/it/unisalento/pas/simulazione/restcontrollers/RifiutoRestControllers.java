package it.unisalento.pas.simulazione.restcontrollers;

import it.unisalento.pas.simulazione.domain.Cassonetto;
import it.unisalento.pas.simulazione.domain.Rifiuto;
import it.unisalento.pas.simulazione.dto.CassonettoDTO;
import it.unisalento.pas.simulazione.dto.RifiutoDTO;
import it.unisalento.pas.simulazione.repository.CassonettoRepository;
import it.unisalento.pas.simulazione.repository.RifiutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("sim/rifiuti/")
public class RifiutoRestControllers {
    @Autowired
    RifiutoRepository rifiutoRepository;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/inserisci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RifiutoDTO postInserisci(@RequestBody RifiutoDTO rifiutoDTO){
        Rifiuto newRifiuto = new Rifiuto();
        newRifiuto.setLuogo(rifiutoDTO.getLuogo());
        newRifiuto.setTipo(rifiutoDTO.getTipo());
        newRifiuto.setUsername(rifiutoDTO.getUsername());
        //salviamo il nuovo utente con l'id aggiornato nel database
        newRifiuto = rifiutoRepository.save(newRifiuto);
        return rifiutoDTO;
    }
}
