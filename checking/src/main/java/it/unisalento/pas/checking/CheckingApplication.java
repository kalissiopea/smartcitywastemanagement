package it.unisalento.pas.checking;

import it.unisalento.pas.checking.configuration.SecurityConfig;
import it.unisalento.pas.checking.dto.CheckingDTO;
import it.unisalento.pas.checking.dto.UtenteDTO;
import it.unisalento.pas.checking.restcontrollers.CheckRestControllers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CheckingApplication {

    private static CheckRestControllers checkRestControllers = new CheckRestControllers();

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CheckingApplication.class, args);
        RestTemplate restTemplate = context.getBean(SecurityConfig.class).restTemplate();

        List<UtenteDTO> utentiDTO = checkRestControllers.getUtenti(restTemplate);
        List<CheckingDTO> checks = new ArrayList<>();
        List<CheckingDTO> newDati = new ArrayList<>();

        int i = 1;
        String identity = Integer.toString(i);
        for(UtenteDTO utenteDTO : utentiDTO) {
            checks = checkRestControllers.getCheckings(utenteDTO.getUsername(), restTemplate);

            if(checks.isEmpty()) {
                checks = checkRestControllers.generareChecking(restTemplate);
                String postCheck = "http://localhost:8080/check/performance/aggiungi";
//                String postGiud = "http://35.172.101.10:8085/giudizio/check/aggiungi";
//                String postEm = "http://3.211.210.231:8086/cittadino/check/aggiungi";
                String postGiud = "http://giudizio:8080/giudizio/check/aggiungi";
                String postEm = "http://emissione:8080/cittadino/check/aggiungi";

                HttpHeaders headers1 = new HttpHeaders();
                headers1.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

                for(CheckingDTO checkingDTO : checks) {
                    String jsonReq =
                            "{ " + "\"id\":" + "\"" + identity + "\"," +
                                    "\"username\":" + "\"" + checkingDTO.getUsername() + "\"," +
                                    "\"punteggio\":" + checkingDTO.getPunteggio() + "," +
                                    "\"rifiuti\":" +  checkingDTO.getRifiuti() +
                                    "}";
                    HttpEntity<String> request = new HttpEntity<>(jsonReq, headers1);
                    System.out.println(request);
                    ResponseEntity<String> risposta = restTemplate.exchange(postCheck, HttpMethod.POST, request, String.class);
                    ResponseEntity<String> response = restTemplate.exchange(postGiud, HttpMethod.POST, request, String.class);
                    ResponseEntity<String> risposte = restTemplate.exchange(postEm, HttpMethod.POST, request, String.class);
                    i = i + 1;
                }
            } else {
                newDati = checkRestControllers.generareChecking(restTemplate);
                for (CheckingDTO check : newDati) {
                    if(utenteDTO.getUsername().equals(check.getUsername())) {
                        String aggiorna = "http://localhost:8080/check/performance/aggiornaCheck/" + check.getUsername() + "/" + check.getPunteggio() + "/" + check.getRifiuti();
//                        String aggiornaGiud = "http://35.172.101.10:8085/giudizio/check/aggiornaCheck/" + check.getUsername() + "/" + check.getPunteggio() + "/" + check.getRifiuti();
//                        String aggiornaEm = "http://3.211.210.231:8086/cittadino/check/aggiornaCheck/" + check.getUsername() + "/" + check.getPunteggio() + "/" + check.getRifiuti();
                        String aggiornaGiud = "http://gudizio:8080/giudizio/check/aggiornaCheck/" + check.getUsername() + "/" + check.getPunteggio() + "/" + check.getRifiuti();
                        String aggiornaEm = "http://emissione:8080/cittadino/check/aggiornaCheck/" + check.getUsername() + "/" + check.getPunteggio() + "/" + check.getRifiuti();
                        ResponseEntity<String> risposta = restTemplate.exchange(aggiorna, HttpMethod.PUT, null, String.class);
                        ResponseEntity<String> response = restTemplate.exchange(aggiornaGiud, HttpMethod.PUT, null, String.class);
                        ResponseEntity<String> risposte = restTemplate.exchange(aggiornaEm, HttpMethod.PUT, null, String.class);
                    }
                }
            }
        }

        //SpringApplication.run(CheckingApplication.class, args);
    }
}
