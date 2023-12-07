package it.unisalento.pas.giudizio.restcontrollers;

import it.unisalento.pas.giudizio.domain.Checking;
import it.unisalento.pas.giudizio.dto.CheckingDTO;
import it.unisalento.pas.giudizio.repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("giudizio/check/")
public class CheckingRestControllers {

    @Autowired
    CheckingRepository checkingRepository;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<CheckingDTO> getAll() {
        List<CheckingDTO> checkingsDTO = new ArrayList<>();

        for(Checking checking : checkingRepository.findAll()) {
            CheckingDTO checkingDTO = new CheckingDTO();
            checkingDTO.setId(checking.getId());
            checkingDTO.setUsername(checking.getUsername());
            checkingDTO.setRifiuti(checking.getRifiuti());
            checkingDTO.setPunteggio(checking.getPunteggio());
            checkingsDTO.add(checkingDTO);
        }
        return checkingsDTO;
    }

    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody CheckingDTO checkingDTO){
        Checking newChecking = new Checking();
        newChecking.setId(checkingDTO.getId());
        newChecking.setUsername(checkingDTO.getUsername());
        newChecking.setPunteggio(checkingDTO.getPunteggio());
        newChecking.setRifiuti(checkingDTO.getRifiuti());
        newChecking = checkingRepository.save(newChecking);
        System.out.println("L'Id nuovo è: " + newChecking.getId());
    }

    @RequestMapping(value = "/aggiornaCheck/{username}/{punteggio}/{rifiuti}", method = RequestMethod.PUT)
    public void aggiornaPunti(@PathVariable String username, @PathVariable int punteggio, @PathVariable int rifiuti) {
        Checking checking = checkingRepository.findByUsername(username);
        if (checking != null) {
            checking.setPunteggio(punteggio);
            checking.setRifiuti(rifiuti);
            checkingRepository.save(checking);
            System.out.println("Stato del punteggio aggiornato con successo.");
        } else {
            System.out.println("Check non trovato.");
        }
    }

    @RequestMapping(value = "/cancella", method = RequestMethod.DELETE)
    public int deleteByUsername (@RequestParam String username) {
        int result = checkingRepository.deleteByUsername(username);
        return result;
    }
}
