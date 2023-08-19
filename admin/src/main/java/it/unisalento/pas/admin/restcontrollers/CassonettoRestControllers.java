package it.unisalento.pas.admin.restcontrollers;

import it.unisalento.pas.admin.domain.Cassonetto;
import it.unisalento.pas.admin.dto.CassonettoDTO;
import it.unisalento.pas.admin.repository.CassonettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin/cassonetti") //ascolta in localhost:8080/admin/cassonetti
public class CassonettoRestControllers {

    @Autowired
    CassonettoRepository cassonettoRepository;

    //INIZIO CICLO CRUD
    //getall
    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<CassonettoDTO> getAll() {
        List<CassonettoDTO> cassonetti = new ArrayList<>();

        for(Cassonetto cassonetto : cassonettoRepository.findAll()) {
            CassonettoDTO cassonettoDTO = new CassonettoDTO();
            cassonettoDTO.setId(cassonetto.getId());
            cassonettoDTO.setLuogo(cassonetto.getLuogo());
            cassonettoDTO.setStato(cassonetto.getStato());
            cassonetti.add(cassonettoDTO);
        }
        return cassonetti;
    }

    //findByStato
    @RequestMapping(value = "/stati", method = RequestMethod.GET)
    public List<CassonettoDTO> findByStato (@RequestParam float stato) {

        List<Cassonetto> result = cassonettoRepository.findByStato(stato);
        List<CassonettoDTO> cassonetti = new ArrayList<>();

        for(Cassonetto cassonetto : result) {
            CassonettoDTO cassonettoDTO = new CassonettoDTO();
            cassonettoDTO.setId(cassonetto.getId());
            cassonettoDTO.setLuogo(cassonetto.getLuogo());
            cassonettoDTO.setStato(cassonetto.getStato());
            cassonetti.add(cassonettoDTO);
        }

        return cassonetti;
    }

    //post
    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CassonettoDTO post(@RequestBody CassonettoDTO cassonettoDTO){
        Cassonetto newCassonetto = new Cassonetto();
        newCassonetto.setId(cassonettoDTO.getId());
        newCassonetto.setLuogo(cassonettoDTO.getLuogo());
        newCassonetto.setStato(cassonettoDTO.getStato());
        newCassonetto = cassonettoRepository.save(newCassonetto);
        System.out.println("L'Id del nuovo cassonetto è: " + newCassonetto.getId());
        return cassonettoDTO;
    }

    //delete
    @RequestMapping(value = "/cancella", method = RequestMethod.DELETE)
    public int deleteByLuogo (@RequestParam String luogo) {
        int result = cassonettoRepository.deleteByLuogo(luogo);
        return result;
    }
}
