package it.unisalento.pas.simulazione;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unisalento.pas.simulazione.broker.Emettitore;
import it.unisalento.pas.simulazione.broker.Ricevitore;
import it.unisalento.pas.simulazione.dto.CassonettoDTO;
import it.unisalento.pas.simulazione.dto.RifiutoDTO;
import it.unisalento.pas.simulazione.dto.UtenteDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class SimulazioneApplication {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConfigurableApplicationContext context = SpringApplication.run(SimulazioneApplication.class, args);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        //aggiornamento dati che servono per il broker
        String postUtenti = "http://localhost:8080/sim/utenti/inserisci";
        restTemplate.postForObject(postUtenti, null, void.class);

        String postCass = "http://localhost:8080/sim/cassonetti/inserisci";
        restTemplate.postForObject(postCass, null, void.class);

        String getUtenti = "http://localhost:8080/sim/utenti/lista";
        HttpHeaders header = new HttpHeaders();
        header.set("Header-Name", "Header-Value");
        HttpEntity<String> entita = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate.exchange(getUtenti, HttpMethod.GET, entita, String.class);
        String body = response.getBody();
        JSONArray arrayJson = new JSONArray(body);
        List<UtenteDTO> utentiDTO = new ArrayList<>();
        for (int i = 0; i < arrayJson.length(); i++) {
            JSONObject jsonObject = arrayJson.getJSONObject(i);
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

        String getCass = "http://localhost:8080/sim/cassonetti/lista";
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

        Ricevitore ricevitore = new Ricevitore(cassonettiDTO, utentiDTO);
        CompletableFuture<RifiutoDTO> completableFuture = ricevitore.ricevere();

        Emettitore emettitore = new Emettitore(cassonettiDTO);

        //SpringApplication.run(SimulazioneApplication.class, args);

        emettitore.emettere();
        try {
            RifiutoDTO rifiutoDTO = completableFuture.get();
            System.out.println(rifiutoDTO.getId() + " " + rifiutoDTO.getUsername() + " " + rifiutoDTO.getLuogo() + " " + rifiutoDTO.getTipo());
/*            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(rifiutoDTO);*/
            String postRif = "http://localhost:8080/sim/rifiuti/inserisci";
            HttpHeaders headers1 = new HttpHeaders();
            headers1.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

            String jsonReq =
                    "{ \"tipo\":" + "\"" + rifiutoDTO.getTipo() + "\"," +
                            "\"luogo\":" + "\"" + rifiutoDTO.getLuogo() + "\"," +
                            "\"username\":" + "\"" + rifiutoDTO.getUsername() + "\"" +
                            "}";

            HttpEntity<String> request = new HttpEntity<>(jsonReq, headers1);
            ResponseEntity<String> risposta = restTemplate.exchange(postRif, HttpMethod.POST, request, String.class);

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
