package it.unisalento.pas.giudizio.restcontrollers;

import it.unisalento.pas.giudizio.domain.Utente;
import it.unisalento.pas.giudizio.dto.UtenteDTO;
import it.unisalento.pas.giudizio.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static it.unisalento.pas.giudizio.configuration.SecurityConfig.passwordEncoder;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("giudizio/utenti/")
public class UtenteRestControllers {

    @Autowired
    UtenteRepository utenteRepository;

    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UtenteDTO postAggiungi(@RequestBody UtenteDTO utenteDTO){
        Utente newUtente = new Utente();
        newUtente.setId(utenteDTO.getId());
        newUtente.setNome(utenteDTO.getNome());
        newUtente.setCognome(utenteDTO.getCognome());
        newUtente.setEmail(utenteDTO.getEmail());
        newUtente.setEta(utenteDTO.getEta());
        newUtente.setUsername(utenteDTO.getUsername());
        newUtente.setPassword(passwordEncoder().encode(utenteDTO.getPassword()));
        newUtente.setRuolo(utenteDTO.getRuolo());
        newUtente.setIndirizzo(utenteDTO.getIndirizzo());
        newUtente.setCard(utenteDTO.getCard());
        //salviamo il nuovo utente con l'id aggiornato nel database
        newUtente = utenteRepository.save(newUtente);
        System.out.println("L'Id del nuovo utente è: " + newUtente.getId());
        return utenteDTO;
    }

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<UtenteDTO> getAll() {
        List<UtenteDTO> utenti = new ArrayList<>();

        for(Utente utente : utenteRepository.findAll()) {
            UtenteDTO utenteDTO = new UtenteDTO();
            utenteDTO.setId(utente.getId());
            utenteDTO.setNome(utente.getNome());
            utenteDTO.setCognome(utente.getCognome());
            utenteDTO.setEmail(utente.getEmail());
            utenteDTO.setEta(utente.getEta());
            utenteDTO.setUsername(utente.getUsername());
            //utenteDTO.setPassword(utente.getPassword());
            utenteDTO.setRuolo(utente.getRuolo());
            utenteDTO.setIndirizzo(utente.getIndirizzo());
            utenteDTO.setCard(utente.getCard());
            utenti.add(utenteDTO);
        }
        return utenti;
    }
}
