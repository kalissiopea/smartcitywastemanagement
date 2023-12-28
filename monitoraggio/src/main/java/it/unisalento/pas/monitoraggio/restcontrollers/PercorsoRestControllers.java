package it.unisalento.pas.monitoraggio.restcontrollers;

import it.unisalento.pas.monitoraggio.domain.Percorso;
import it.unisalento.pas.monitoraggio.dto.PercorsoDTO;
import it.unisalento.pas.monitoraggio.repository.PercorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("percorso/")
public class PercorsoRestControllers {

    @Autowired
    PercorsoRepository percorsoRepository;

    @Autowired
    RestTemplate restTemplate;

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
        //pulizia cassonetti
        String[] luoghi = dividi(percorsoDTO.getPercorso());
        for(String luogo : luoghi) {
            String aggiorna = "http://localhost:8080/monitorare/cassonetti/aggiornaStato/" + luogo + "/" + 0.0;
            String aggiornaAdmin = "http://52.54.199.145:8080/admin/cassonetti/aggiornaStato/" + luogo + "/" + 0.0;
            String aggiornaCheck = "http://34.193.105.215:8083/check/cassonetti/aggiornaStato/" + luogo + "/" + 0.0;
            String aggiornaSim = "http://54.211.179.82:8000/sim/cassonetti/aggiornaStato/" + luogo + "/" + 0.0;
//            String aggiornaAdmin = "http://admin:8080/admin/cassonetti/aggiornaStato/" + luogo + "/" + 0.0;
//            String aggiornaCheck = "http://checking:8080/check/cassonetti/aggiornaStato/" + luogo + "/" + 0.0;
//            String aggiornaSim = "http://simulazione:8000/sim/cassonetti/aggiornaStato/" + luogo + "/" + 0.0;
            ResponseEntity<String> risposta1 = restTemplate.exchange(aggiorna, HttpMethod.PUT, null, String.class);
            ResponseEntity<String> risposta2 = restTemplate.exchange(aggiornaAdmin, HttpMethod.PUT, null, String.class);
            ResponseEntity<String> risposta3 = restTemplate.exchange(aggiornaCheck, HttpMethod.PUT, null, String.class);
            ResponseEntity<String> risposta4 = restTemplate.exchange(aggiornaSim, HttpMethod.PUT, null, String.class);
        }
        System.out.println("L'Id del nuovo percorso è: " + newPercorso.getId());
        return percorsoDTO;
    }

    @RequestMapping(value = "/cancella", method = RequestMethod.DELETE)
    public int deleteByDate (@RequestParam String date) {
        int result = percorsoRepository.deleteByDate(date);
        return result;
    }

    public String[] dividi(String percorso) {
        String[] splitted = percorso.split(",");
        return splitted;
    }

}
