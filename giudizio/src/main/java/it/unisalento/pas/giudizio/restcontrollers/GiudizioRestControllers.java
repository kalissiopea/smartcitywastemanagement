package it.unisalento.pas.giudizio.restcontrollers;

import it.unisalento.pas.giudizio.domain.Giudizio;
import it.unisalento.pas.giudizio.dto.GiudizioDTO;
import it.unisalento.pas.giudizio.repository.GiudizioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("giudizio/consulta/")
public class GiudizioRestControllers {

    @Autowired
    GiudizioRepository giudizioRepository;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<GiudizioDTO> getAll() {
        List<GiudizioDTO> giudiziDTO = new ArrayList<>();

        for(Giudizio giudizio : giudizioRepository.findAll()) {
            GiudizioDTO giudizioDTO = new GiudizioDTO();
            giudizioDTO.setId(giudizio.getId());
            giudizioDTO.setUsername(giudizio.getUsername());
            giudizioDTO.setGiudizio(giudizio.getGiudizio());
            giudizioDTO.setPunteggio(giudizio.getPunteggio());
            giudizioDTO.setDate(giudizio.getDate());
            giudiziDTO.add(giudizioDTO);
        }
        return giudiziDTO;
    }

    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody GiudizioDTO giudizioDTO){
        Giudizio giudizio = new Giudizio();
        giudizio.setId(giudizioDTO.getId());
        giudizio.setUsername(giudizioDTO.getUsername());
        giudizio.setPunteggio(giudizioDTO.getPunteggio());
        giudizio.setGiudizio(giudizioDTO.getGiudizio());
        giudizioDTO.setDate(new Date().toString());
        giudizio.setDate(giudizioDTO.getDate());
        giudizio = giudizioRepository.save(giudizio);
        String url = "http://checking:8080/check/giudizio/aggiungi";
        System.out.println(postApi(giudizioDTO, url));
        System.out.println("L'Id nuovo è: " + giudizio.getId());
    }

    public String postApi(GiudizioDTO giudizioDTO, String url) {
        // Creazione dell'header della richiesta
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        String jsonReq =
                "{ \"id\":" + "\"" + giudizioDTO.getId() + "\"," +
                        "\"username\":" + "\"" + giudizioDTO.getUsername() + "\"," +
                        "\"punteggio\":" + "\"" + giudizioDTO.getPunteggio() + "\"," +
                        "\"giudizio\":" + "\"" + giudizioDTO.getGiudizio() + "\"," +
                        "\"date\":" + "\"" + giudizioDTO.getDate() + "\"" +
                        "}";

        // Creazione dell'oggetto HttpEntity con header e parametri
        HttpEntity<String> request = new HttpEntity<>(jsonReq, headers);

        // Invio della richiesta POST all'URL specificato
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            return "Risposta: " + responseBody;
        }
        return "Errore nella richiesta";
    }
}
