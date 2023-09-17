package it.unisalento.pas.monitoraggio.restcontrollers;

import it.unisalento.pas.monitoraggio.domain.Percorso;
import it.unisalento.pas.monitoraggio.dto.PercorsoDTO;
import it.unisalento.pas.monitoraggio.repository.PercorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("percorso/")
public class PercorsoRestControllers {

    @Autowired
    PercorsoRepository percorsoRepository;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<PercorsoDTO> getAll() {
        List<PercorsoDTO> percorsiDTO = new ArrayList<>();

        for(Percorso percorso : percorsoRepository.findAll()) {
            PercorsoDTO percorsoDTO = new PercorsoDTO();
            percorsoDTO.setId(percorso.getId());
            percorsoDTO.setPercorso(percorso.getPercorso());
            percorsoDTO.setDate(percorso.getDate());
            percorsiDTO.add(percorsoDTO);
        }
        return percorsiDTO;
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public List<PercorsoDTO> getByData(@RequestParam String date) {
        List<PercorsoDTO> percorsiDTO = new ArrayList<>();

        for(Percorso percorso : percorsoRepository.findByDate(date)) {
            PercorsoDTO percorsoDTO = new PercorsoDTO();
            percorsoDTO.setId(percorso.getId());
            percorsoDTO.setPercorso(percorso.getPercorso());
            percorsoDTO.setDate(percorso.getDate());
            percorsiDTO.add(percorsoDTO);
        }
        return percorsiDTO;
    }

    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PercorsoDTO post(@RequestBody PercorsoDTO percorsoDTO){
        Percorso newPercorso = new Percorso();
        newPercorso.setId(percorsoDTO.getId());
        newPercorso.setPercorso(percorsoDTO.getPercorso());
        newPercorso.setDate(percorsoDTO.getDate());
        newPercorso = percorsoRepository.save(newPercorso);
        System.out.println("L'Id del nuovo percorso è: " + newPercorso.getId());
        return percorsoDTO;
    }

    @RequestMapping(value = "/cancella", method = RequestMethod.DELETE)
    public int deleteByDate (@RequestParam String date) {
        int result = percorsoRepository.deleteByDate(date);
        return result;
    }

}
