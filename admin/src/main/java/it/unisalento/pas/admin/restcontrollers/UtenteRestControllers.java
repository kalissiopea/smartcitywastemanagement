package it.unisalento.pas.admin.restcontrollers;
//qui si mettono i metodi che l'admin usa all'interno del database per gestire i dati

import it.unisalento.pas.admin.domain.Utente;
import it.unisalento.pas.admin.dto.UtenteDTO;
import it.unisalento.pas.admin.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static it.unisalento.pas.admin.configuration.SecurityConf.passwordEncoder;

@RestController
@RequestMapping("admin/utenti") //ascolta in localhost:8080/admin/utenti
//dovrebbe essere la pagina principale dell'admin dopo che viene fatto il login
public class UtenteRestControllers {

    //utente repository usa con utente del domain per gestire i dati nel database
    @Autowired
    UtenteRepository utenteRepository;

    //INIZIO CICLO CRUD
    //getall utenti
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

    //findByRuolo utente
    @RequestMapping(value = "/ruoli", method = RequestMethod.GET)
    public List<UtenteDTO> findByRuolo (@RequestParam String ruolo) {

        List<Utente> result = utenteRepository.findByRuolo(ruolo);
        List<UtenteDTO> utenti = new ArrayList<>();

        for(Utente utente : result) {
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

    //post utente
    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UtenteDTO post(@RequestBody UtenteDTO utenteDTO){
        Utente newUtente = new Utente();
        newUtente.setId(utenteDTO.getId());
        newUtente.setNome(utenteDTO.getNome());
        newUtente.setCognome(utenteDTO.getCognome());
        newUtente.setEmail(utenteDTO.getEmail());
        newUtente.setEta(utenteDTO.getEta());
        newUtente.setUsername(utenteDTO.getUsername());
        newUtente.setPassword(passwordEncoder().encode(utenteDTO.getPassword()));
        //newUtente.setPassword(utenteDTO.getPassword());
        newUtente.setRuolo(utenteDTO.getRuolo());
        newUtente.setIndirizzo(utenteDTO.getIndirizzo());
        newUtente.setCard(utenteDTO.getCard());
        //salviamo il nuovo utente con l'id aggiornato nel database
        newUtente = utenteRepository.save(newUtente);
        System.out.println("L'Id del nuovo utente è: " + newUtente.getId());
        return utenteDTO;
    }

    //delete
    @RequestMapping(value = "/cancella", method = RequestMethod.DELETE)
    public int deleteByUsername (@RequestParam String username) {
        int result = utenteRepository.deleteByUsername(username);
        return result;
    }

}
