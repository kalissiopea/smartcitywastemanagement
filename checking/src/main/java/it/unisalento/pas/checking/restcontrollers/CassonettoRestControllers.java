package it.unisalento.pas.checking.restcontrollers;

import it.unisalento.pas.checking.domain.Cassonetto;
import it.unisalento.pas.checking.dto.CassonettoDTO;
import it.unisalento.pas.checking.repository.CassonettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("check/cassonetti")
public class CassonettoRestControllers {
    @Autowired
    CassonettoRepository cassonettoRepository;

    //permit all
    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CassonettoDTO post(@RequestBody CassonettoDTO cassonettoDTO){
        Cassonetto newCassonetto = new Cassonetto();
        newCassonetto.setId(cassonettoDTO.getId());
        newCassonetto.setLuogo(cassonettoDTO.getLuogo());
        newCassonetto.setTipo(cassonettoDTO.getTipo());
        newCassonetto.setStato(cassonettoDTO.getStato());
        newCassonetto = cassonettoRepository.save(newCassonetto);
        System.out.println("L'Id del nuovo cassonetto è: " + newCassonetto.getId());
        return cassonettoDTO;
    }

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<CassonettoDTO> getAll() {
        List<CassonettoDTO> cassonetti = new ArrayList<>();

        for(Cassonetto cassonetto : cassonettoRepository.findAll()) {
            CassonettoDTO cassonettoDTO = new CassonettoDTO();
            cassonettoDTO.setId(cassonetto.getId());
            cassonettoDTO.setLuogo(cassonetto.getLuogo());
            cassonettoDTO.setTipo(cassonetto.getTipo());
            cassonettoDTO.setStato(cassonetto.getStato());
            cassonetti.add(cassonettoDTO);
        }
        return cassonetti;
    }

    @RequestMapping(value = "/aggiornaStato/{luogo}/{stato}", method = RequestMethod.PUT)
    public void aggiornaStato(@PathVariable String luogo, @PathVariable float stato){
        Cassonetto cassonetto = cassonettoRepository.findByLuogo(luogo);
        if(cassonetto != null) {
            float statoAtt = cassonetto.getStato();
            if(stato != 0.0) {
                cassonetto.setStato(statoAtt + stato);
            } else {
                cassonetto.setStato(stato);
            }
            cassonettoRepository.save(cassonetto);
            System.out.println("Stato del cassonetto aggiornato con successo.");
        } else {
            System.out.println("Cassonetto non trovato.");
        }
    }
}
