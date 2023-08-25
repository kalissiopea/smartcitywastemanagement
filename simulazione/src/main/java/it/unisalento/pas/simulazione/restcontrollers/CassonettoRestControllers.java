package it.unisalento.pas.simulazione.restcontrollers;

import it.unisalento.pas.simulazione.domain.Cassonetto;
import it.unisalento.pas.simulazione.dto.CassonettoDTO;
import it.unisalento.pas.simulazione.repository.CassonettoRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("sim/cassonetti/")
public class CassonettoRestControllers {

    @Autowired
    CassonettoRepository cassonettoRepository;

    @Autowired
    RestTemplate restTemplate;

    //aggiorniamo il db di questo microservizio con i cittadini
    @RequestMapping(value = "/inserisci", method = RequestMethod.POST)
    public void postInserisci(){
        List<CassonettoDTO> cassonettiDTO = getCassResponse();
        for(CassonettoDTO cassonettoDTO : cassonettiDTO) {
            Cassonetto newCassonetto = new Cassonetto();
            newCassonetto.setId(cassonettoDTO.getId());
            newCassonetto.setLuogo(cassonettoDTO.getLuogo());
            newCassonetto.setTipo(cassonettoDTO.getTipo());
            newCassonetto.setStato(cassonettoDTO.getStato());

            //salviamo il nuovo utente con l'id aggiornato nel database
            newCassonetto = cassonettoRepository.save(newCassonetto);
            System.out.println("L'Id del nuovo cassonetto è: " + newCassonetto.getId());
        }
    }

    //api per prelevare i cassonetti dall'admin
    public List<CassonettoDTO> getCassResponse() {
        String url = "http://admin:8080/admin/cassonetti/lista";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Header-Name", "Header-Value");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = response.getBody();

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
        return cassonettiDTO;
    }

    //get a questo db
    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<CassonettoDTO> getAll() {
        List<CassonettoDTO> cassonetti = new ArrayList<>();

        for(Cassonetto cassonetto : cassonettoRepository.findAll()) {
            CassonettoDTO cassonettoDTO = new CassonettoDTO();
            cassonettoDTO.setId(cassonetto.getId());
            cassonettoDTO.setLuogo(cassonetto.getLuogo());
            cassonettoDTO.setTipo(cassonetto.getTipo());
            cassonettoDTO.setStato(cassonetto.getStato());
            cassonetti.add(cassonettoDTO);
        }
        return cassonetti;
    }
}
