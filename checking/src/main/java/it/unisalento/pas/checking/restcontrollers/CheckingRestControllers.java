package it.unisalento.pas.checking.restcontrollers;

import it.unisalento.pas.checking.domain.Checking;
import it.unisalento.pas.checking.dto.CassonettoDTO;
import it.unisalento.pas.checking.dto.CheckingDTO;
import it.unisalento.pas.checking.dto.RifiutoDTO;
import it.unisalento.pas.checking.dto.UtenteDTO;
import it.unisalento.pas.checking.repository.CheckingRepository;
import it.unisalento.pas.checking.security.JwtAuthenticationFilter;
import it.unisalento.pas.checking.security.JwtUtilities;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("check/performance/")
public class CheckingRestControllers {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @RequestMapping(value = "/mie", method = RequestMethod.GET)
    public CheckingDTO getByUsername() {
        String username = getUsername();
        System.out.println(username);
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
