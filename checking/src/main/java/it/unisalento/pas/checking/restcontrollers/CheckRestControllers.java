package it.unisalento.pas.checking.restcontrollers;

import it.unisalento.pas.checking.dto.CassonettoDTO;
import it.unisalento.pas.checking.dto.CheckingDTO;
import it.unisalento.pas.checking.dto.RifiutoDTO;
import it.unisalento.pas.checking.dto.UtenteDTO;
import it.unisalento.pas.checking.repository.CheckingRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@Component
public class CheckRestControllers {
    @Autowired
    CheckingRepository checkingRepository;

//    @Autowired
//    RestTemplate restTemplate;

    public List<CassonettoDTO> getCassonetti(RestTemplate restTemplate){
        String getCass = "http://localhost:8080/check/cassonetti/lista";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Header-Name", "Header-Value");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(getCass, HttpMethod.GET, entity, String.class);
        String responseBody = responseEntity.getBody();
        JSONArray jsonArray = new JSONArray(responseBody);
        List<CassonettoDTO> cassonettiDTO = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            CassonettoDTO cassonettoDTO = new CassonettoDTO();
            cassonettoDTO.setId(jsonObject.getString("id"));
            cassonettoDTO.setLuogo(jsonObject.getString("luogo"));
            cassonettoDTO.setTipo(jsonObject.getString("tipo"));
            cassonettoDTO.setStato(jsonObject.getFloat("stato"));
            cassonettiDTO.add(cassonettoDTO);
        }
        return  cassonettiDTO;
    }

    public List<RifiutoDTO> getRifiuti(RestTemplate restTemplate){
        String getRif = "http://localhost:8080/check/rifiuti/lista";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Header-Name", "Header-Value");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(getRif, HttpMethod.GET, entity, String.class);
        String responseBody = responseEntity.getBody();
        JSONArray jsonArray = new JSONArray(responseBody);
        List<RifiutoDTO> rifiutiDTO = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            RifiutoDTO rifiutoDTO = new RifiutoDTO();
            rifiutoDTO.setId(jsonObject.getString("id"));
            rifiutoDTO.setLuogo(jsonObject.getString("luogo"));
            rifiutoDTO.setTipo(jsonObject.getString("tipo"));
            rifiutoDTO.setUsername(jsonObject.getString("username"));
            rifiutiDTO.add(rifiutoDTO);
        }
        return  rifiutiDTO;
    }

    public List<UtenteDTO> getUtenti(RestTemplate restTemplate){
        String getUsers = "http://localhost:8080/check/utenti/lista";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Header-Name", "Header-Value");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUsers, HttpMethod.GET, entity, String.class);
        String responseBody = responseEntity.getBody();
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
        return  utentiDTO;
    }


    public List<CheckingDTO> generareChecking(RestTemplate restTemplate) {
        List<RifiutoDTO> rifiutiDTO = getRifiuti(restTemplate);
        List<CassonettoDTO> cassonettiDTO = getCassonetti(restTemplate);
        List<UtenteDTO> utentiDTO = getUtenti(restTemplate);
        List<CheckingDTO> checkingsDTO = new ArrayList<>();

        for(UtenteDTO utenteDTO : utentiDTO) {
            int punteggio = 0;
            int rifiuti = 0;
            CheckingDTO checkingDTO = new CheckingDTO();
            for(RifiutoDTO rifiutoDTO : rifiutiDTO) {
                for (CassonettoDTO cassonettoDTO : cassonettiDTO) {
                    if(utenteDTO.getUsername().equals(rifiutoDTO.getUsername()) && rifiutoDTO.getLuogo().equals(cassonettoDTO.getLuogo())) {
                        checkingDTO.setUsername(utenteDTO.getUsername());
                        rifiuti = rifiuti + 1;
                        if(rifiutoDTO.getTipo().equals(cassonettoDTO.getTipo())) {
                            punteggio = punteggio + 1;
                        } else {
                            punteggio = punteggio - 1;
                        }
                    }
                }
            }
            checkingDTO.setRifiuti(rifiuti);
            checkingDTO.setPunteggio(punteggio);
            checkingsDTO.add(checkingDTO);
        }
        return checkingsDTO;
    }

    public List<CheckingDTO> getCheckings(String username, RestTemplate restTemplate){
        List<CheckingDTO> checkingsDTO = new ArrayList<>();
        String getCheck = "http://localhost:8080/check/performance/utente?username=" + username;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Header-Name", "Header-Value");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(getCheck, HttpMethod.GET, entity, String.class);
        System.out.println(responseEntity.getBody());
        if (responseEntity.getBody() == null) {
            return checkingsDTO;
        }
        String responseBody = responseEntity.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        CheckingDTO checkingDTO = new CheckingDTO();
        checkingDTO.setId(jsonObject.getString("id"));
        checkingDTO.setUsername(jsonObject.getString("username"));
        checkingDTO.setRifiuti(jsonObject.getInt("rifiuti"));
        checkingDTO.setPunteggio(jsonObject.getInt("punteggio"));
        checkingsDTO.add(checkingDTO);
        return checkingsDTO;
    }
}
