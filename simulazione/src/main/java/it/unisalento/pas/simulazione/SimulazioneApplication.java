package it.unisalento.pas.simulazione;

import it.unisalento.pas.simulazione.broker.Emettitore;
import it.unisalento.pas.simulazione.broker.Ricevitore;
import it.unisalento.pas.simulazione.dto.CassonettoDTO;
import it.unisalento.pas.simulazione.dto.UtenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class SimulazioneApplication {

    @Autowired
    private static RestTemplate restTemplate;

    public static void main(String[] args) throws IOException, TimeoutException {

        //aggiornamento dati che servono per il broker
        String postUtenti = "http://localhost:8080/sim/utenti/inserisci";
        restTemplate.getForObject(postUtenti, String.class);

        String postCass = "http://localhost:8080/sim/cassonetti/inserisci";
        restTemplate.getForObject(postCass, String.class);

        String getUtenti = "http://localhost:8080/sim/utenti/lista";
        List<UtenteDTO> utentiDTO = new ArrayList<>();
        utentiDTO = restTemplate.getForObject(getUtenti, ArrayList.class);

        String getCass = "http://localhost:8080/sim/cassonetti/lista";
        List<CassonettoDTO> cassonettiDTO = new ArrayList<>();
        cassonettiDTO = restTemplate.getForObject(getCass, ArrayList.class);

        Ricevitore ricevitore = new Ricevitore(cassonettiDTO, utentiDTO);
        ricevitore.ricevere();

        Emettitore emettitore = new Emettitore(cassonettiDTO);

        SpringApplication.run(SimulazioneApplication.class, args);

        emettitore.emettere();

    }
}
