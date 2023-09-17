package it.unisalento.pas.monitoraggio.restcontrollers;

import it.unisalento.pas.monitoraggio.domain.Allarme;
import it.unisalento.pas.monitoraggio.dto.AllarmeDTO;
import it.unisalento.pas.monitoraggio.repository.AllarmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("allarme/")
public class AllarmeRestControllers {

    @Autowired
    AllarmeRepository allarmeRepository;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<AllarmeDTO> getAll() {
        List<AllarmeDTO> allarmiDTO = new ArrayList<>();

        for(Allarme allarme : allarmeRepository.findAll()) {
            AllarmeDTO allarmeDTO = new AllarmeDTO();
            allarmeDTO.setId(allarme.getId());
            allarmeDTO.setAllarme(allarme.isAllarme());
            allarmeDTO.setLuogo(allarme.getLuogo());
            allarmeDTO.setStato(allarme.getStato());
            allarmeDTO.setDate(allarme.getDate());
            allarmiDTO.add(allarmeDTO);
        }
        return allarmiDTO;
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public List<AllarmeDTO> getByData(@RequestParam String date) {
        List<AllarmeDTO> allarmiDTO = new ArrayList<>();

        for(Allarme allarme : allarmeRepository.findByDate(date)) {
            AllarmeDTO allarmeDTO = new AllarmeDTO();
            allarmeDTO.setId(allarme.getId());
            allarmeDTO.setAllarme(allarme.isAllarme());
            allarmeDTO.setLuogo(allarme.getLuogo());
            allarmeDTO.setStato(allarme.getStato());
            allarmeDTO.setDate(allarme.getDate());
            allarmiDTO.add(allarmeDTO);
        }
        return allarmiDTO;
    }

    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AllarmeDTO post(@RequestBody AllarmeDTO allarmeDTO){
        Allarme newAllarme = new Allarme();
        newAllarme.setId(allarmeDTO.getId());
        newAllarme.setAllarme(allarmeDTO.isAllarme());
        newAllarme.setLuogo(allarmeDTO.getLuogo());
        newAllarme.setStato(allarmeDTO.getStato());
        newAllarme.setDate(allarmeDTO.getDate());
        newAllarme = allarmeRepository.save(newAllarme);
        System.out.println("L'Id del nuovo allarme è: " + newAllarme.getId());
        return allarmeDTO;
    }

    @RequestMapping(value = "/cancella", method = RequestMethod.DELETE)
    public int deleteByDate (@RequestParam String date) {
        int result = allarmeRepository.deleteByDate(date);
        return result;
    }
}
