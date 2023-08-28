package it.unisalento.pas.checking.restcontrollers;

import it.unisalento.pas.checking.domain.Checking;
import it.unisalento.pas.checking.dto.CassonettoDTO;
import it.unisalento.pas.checking.dto.CheckingDTO;
import it.unisalento.pas.checking.dto.RifiutoDTO;
import it.unisalento.pas.checking.dto.UtenteDTO;
import it.unisalento.pas.checking.repository.CheckingRepository;
import it.unisalento.pas.checking.security.JwtUtilities;
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
@RequestMapping("check/performance/")
public class CheckingRestControllers {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    RestTemplate restTemplate;
    

    @RequestMapping(value = "/mie", method = RequestMethod.GET)
    public List<CheckingDTO> getByUsername() {
        return null;
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

    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST)
    public void post(){
        List<CheckingDTO> checkingsDTO = generareChecking();
        for(CheckingDTO checkingDTO : checkingsDTO) {
            Checking newChecking = new Checking();
            newChecking.setId(checkingDTO.getId());
            newChecking.setUsername(checkingDTO.getUsername());
            newChecking.setPunteggio(checkingDTO.getPunteggio());
            newChecking.setRifiuti(checkingDTO.getRifiuti());
            newChecking = checkingRepository.save(newChecking);
            System.out.println("L'Id del nuovo cassonetto è: " + newChecking.getId());
        }
    }


    public List<CassonettoDTO> getCassonetti(){
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

    public List<RifiutoDTO> getRifiuti(){
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

    public List<UtenteDTO> getUtenti(){
        String getUtenti = "http://localhost:8080/check/utenti/lista";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Header-Name", "Header-Value");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUtenti, HttpMethod.GET, entity, String.class);
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


    public List<CheckingDTO> generareChecking() {
        List<RifiutoDTO> rifiutiDTO = getRifiuti();
        List<CassonettoDTO> cassonettiDTO = getCassonetti();
        List<UtenteDTO> utentiDTO = getUtenti();
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

}
