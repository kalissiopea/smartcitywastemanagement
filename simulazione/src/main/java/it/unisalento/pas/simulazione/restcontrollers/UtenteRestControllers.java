package it.unisalento.pas.simulazione.restcontrollers;

import it.unisalento.pas.simulazione.domain.Utente;
import it.unisalento.pas.simulazione.dto.CassonettoDTO;
import it.unisalento.pas.simulazione.dto.UtenteDTO;
import it.unisalento.pas.simulazione.repository.UtenteRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("sim/utenti/")
public class UtenteRestControllers {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    RestTemplate restTemplate;

    //aggiorniamo il db di questo microservizio con i cittadini
    @RequestMapping(value = "/inserisci", method = RequestMethod.POST/*, consumes = MediaType.APPLICATION_JSON_VALUE*/)
    public void postInserisci(/*@RequestBody UtenteDTO utenteDTO*/){
        List<UtenteDTO> utentiDTO = getUtentiResponse();
        for(UtenteDTO utenteDTO : utentiDTO) {
            Utente newUtente = new Utente();
            newUtente.setId(utenteDTO.getId());
            newUtente.setNome(utenteDTO.getNome());
            newUtente.setCognome(utenteDTO.getCognome());
            newUtente.setEmail(utenteDTO.getEmail());
            newUtente.setEta(utenteDTO.getEta());
            newUtente.setUsername(utenteDTO.getUsername());
            //newUtente.setPassword(passwordEncoder().encode(utenteDTO.getPassword()));
            //newUtente.setPassword(utenteDTO.getPassword());
            newUtente.setRuolo(utenteDTO.getRuolo());
            newUtente.setIndirizzo(utenteDTO.getIndirizzo());
            newUtente.setCard(utenteDTO.getCard());

            //salviamo il nuovo utente con l'id aggiornato nel database
            newUtente = utenteRepository.save(newUtente);
            System.out.println("L'Id del nuovo utente è: " + newUtente.getId());
        }
    }

    //api che prende tutti i cittadini dal db dell'admin
    public List<UtenteDTO> getUtentiResponse() {
        String url = "http://admin:8080/admin/utenti/ruoli?ruolo=cittadino";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Header-Name", "Header-Value");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = response.getBody();

        JSONArray jsonArray = new JSONArray(responseBody);
        List<UtenteDTO> utentiDTO = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            UtenteDTO utenteDTO = new UtenteDTO();
            utenteDTO.setId(jsonObject.getString("id"));
            utenteDTO.setNome(jsonObject.getString("nome"));
            utenteDTO.setCognome(jsonObject.getString("cognome"));
            utenteDTO.setEmail(jsonObject.getString("email"));
            utenteDTO.setEta(jsonObject.getInt("eta"));
            utenteDTO.setUsername(jsonObject.getString("username"));
            utenteDTO.setRuolo(jsonObject.getString("ruolo"));
            utenteDTO.setIndirizzo("indirizzo");
            utenteDTO.setCard("card");
            utentiDTO.add(utenteDTO);

        }
        return utentiDTO;
    }

    //get di questo db
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
