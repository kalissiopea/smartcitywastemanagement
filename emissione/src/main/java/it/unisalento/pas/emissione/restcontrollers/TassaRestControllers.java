package it.unisalento.pas.emissione.restcontrollers;

import it.unisalento.pas.emissione.domain.Tassa;
import it.unisalento.pas.emissione.dto.CheckingDTO;
import it.unisalento.pas.emissione.dto.TassaDTO;
import it.unisalento.pas.emissione.repository.TassaRepository;
import it.unisalento.pas.emissione.security.JwtAuthenticationFilter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("cittadino/tassa")
public class TassaRestControllers {

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    TassaRepository tassaRepository;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<TassaDTO> getAll() {
        List<TassaDTO> tasse = new ArrayList<>();

        for (Tassa tassa : tassaRepository.findAll()){
            TassaDTO tassaDTO = new TassaDTO();
            tassaDTO.setId(tassa.getId());
            tassaDTO.setData(tassa.getData());
            tassaDTO.setImporto(tassa.getImporto());
            tassaDTO.setUsername(tassa.getUsername());

            tasse.add(tassaDTO);
        }
        return tasse;
    }

    @RequestMapping(value = "/mie",method = RequestMethod.GET)
    public List<TassaDTO> findByUsername(){
        String username = getUsername();
        List<Tassa> result= tassaRepository.findByUsername(username);
        List<TassaDTO> tasse = new ArrayList<>();

        for (Tassa tassa : result){
            TassaDTO tassaDTO = new TassaDTO();
            tassaDTO.setId(tassa.getId());
            tassaDTO.setData(tassa.getData());
            tassaDTO.setImporto(tassa.getImporto());
            tassaDTO.setUsername(tassa.getUsername());
            tasse.add(tassaDTO);
        }
        return tasse;
    }

    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody TassaDTO tassaDTO){
        Tassa newTassa = new Tassa();
        newTassa.setId(tassaDTO.getId());
        newTassa.setUsername(tassaDTO.getUsername());
        tassaDTO.setImporto(getImporto(tassaDTO.getUsername()));
        newTassa.setImporto(tassaDTO.getImporto());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = sdf.format(date);
        tassaDTO.setData(formattedDate);
        newTassa.setData(formattedDate);
        newTassa = tassaRepository.save(newTassa);
//        String url = "http://18.207.172.198:8082/cittadino/tassa/aggiungi";
        String url = "http://pagare:8080/cittadino/tassa/aggiungi";
        System.out.println(postApi(tassaDTO, url));
        System.out.println("L'Id nuovo è: " + newTassa.getId());
    }

    public String getUsername() {
        return jwtAuthenticationFilter.getUsername();
    }

    public String postApi(TassaDTO tassaDTO, String url) {
        // Creazione dell'header della richiesta
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        String jsonReq =
                "{ \"id\":" + "\"" + tassaDTO.getId() + "\"," +
                        "\"username\":" + "\"" + tassaDTO.getUsername() + "\"," +
                        "\"importo\":" + "\"" + tassaDTO.getImporto() + "\"," +
                        "\"data\":" + "\"" + tassaDTO.getData() + "\"" +
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

    public CheckingDTO getApi(String url) {
        HttpHeaders header = new HttpHeaders();
        header.set("Header-Name", "Header-Value");
        HttpEntity<String> entita = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entita, String.class);
        String body = response.getBody();
        JSONObject object = new JSONObject(body);
        CheckingDTO checkingDTO = new CheckingDTO();
        checkingDTO.setId(object.getString("id"));
        checkingDTO.setRifiuti(object.getInt("rifiuti"));
        checkingDTO.setPunteggio(object.getInt("punteggio"));
        checkingDTO.setUsername(object.getString("username"));
        return checkingDTO;
    }

    public float getImporto(String username){
        float importo = 300;
        float pegno = 10;
        String url = "http://localhost:8080/cittadino/check/utente?username=" + username;
        int punteggio = getApi(url).getPunteggio();
        if(punteggio < 0) {
            importo = importo + (Math.abs(punteggio) * pegno);
        } else {
            importo = importo - (Math.abs(punteggio) * pegno);
        }
        return importo;
    }
}
