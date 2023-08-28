package it.unisalento.pas.checking.restcontrollers;

import it.unisalento.pas.checking.domain.Cassonetto;
import it.unisalento.pas.checking.dto.CassonettoDTO;
import it.unisalento.pas.checking.repository.CassonettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
