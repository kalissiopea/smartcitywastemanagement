package it.unisalento.pas.emissione.restcontrollers;

import it.unisalento.pas.emissione.domain.Checking;
import it.unisalento.pas.emissione.dto.CheckingDTO;
import it.unisalento.pas.emissione.repository.CheckingRepository;
import it.unisalento.pas.emissione.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("cittadino/check/")
public class CheckingRestControllers {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @RequestMapping(value = "/mie", method = RequestMethod.GET)
    public CheckingDTO getByUsername() {
        String username = getUsername();
        Checking checking = checkingRepository.findByUsername(username);
        CheckingDTO checkingDTO = new CheckingDTO();
        checkingDTO.setId(checking.getId());
        checkingDTO.setUsername(checking.getUsername());
        checkingDTO.setRifiuti(checking.getRifiuti());
        checkingDTO.setPunteggio(checking.getPunteggio());
        return checkingDTO;
    }

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

    @RequestMapping(value = "/utente", method = RequestMethod.GET)
    public CheckingDTO getByUsername(@RequestParam String username) {

        Checking checking = checkingRepository.findByUsername(username);
        CheckingDTO checkingDTO = new CheckingDTO();
        if(checking != null) {
            checkingDTO.setId(checking.getId());
            checkingDTO.setUsername(checking.getUsername());
            checkingDTO.setRifiuti(checking.getRifiuti());
            checkingDTO.setPunteggio(checking.getPunteggio());
            return checkingDTO;
        }
        return null;
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

    public String getUsername() {
        return jwtAuthenticationFilter.getUsername();
    }

    @RequestMapping(value = "/cancella", method = RequestMethod.DELETE)
    public int deleteByUsername (@RequestParam String username) {
        int result = checkingRepository.deleteByUsername(username);
        return result;
    }
}
