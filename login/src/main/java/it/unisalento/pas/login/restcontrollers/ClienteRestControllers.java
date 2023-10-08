package it.unisalento.pas.login.restcontrollers;

import it.unisalento.pas.login.domain.Cliente;
import it.unisalento.pas.login.dto.AuthenticationResponseDTO;
import it.unisalento.pas.login.dto.ClienteDTO;
import it.unisalento.pas.login.dto.LoginDTO;
import it.unisalento.pas.login.repository.ClienteRepository;
import it.unisalento.pas.login.security.JwtUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import static it.unisalento.pas.login.configuration.SecurityConf.passwordEncoder;

@RestController
@RequestMapping("utenti/")
public class ClienteRestControllers {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    RestTemplate restTemplate;

    //una volta passati username e password, lui controlla se l'utente esiste o meno
    @Autowired
    private AuthenticationManager authenticationManager;

    //serve a generare il token
    @Autowired
    private JwtUtilities jwtUtilities;

    //getall utenti
    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public java.util.List<ClienteDTO> getAll() {
        List<ClienteDTO> clienti = new ArrayList<>();

/*        List<Cliente> altriUtenti = getApiResponse();
        List<Cliente> questiClienti = clienteRepository.findAll();
        List<Cliente> tuttiUtenti = new ArrayList<>(altriUtenti);
        tuttiUtenti.addAll(questiClienti);*/

        //for(Cliente cliente : tuttiUtenti) {
        for(Cliente cliente : clienteRepository.findAll()) {
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setId(cliente.getId());
                clienteDTO.setNome(cliente.getNome());
                clienteDTO.setCognome(cliente.getCognome());
                clienteDTO.setEmail(cliente.getEmail());
                clienteDTO.setEta(cliente.getEta());
                clienteDTO.setUsername(cliente.getUsername());
                //clienteDTO.setPassword(cliente.getPassword());
                clienteDTO.setRuolo(cliente.getRuolo());
                clienteDTO.setIndirizzo(cliente.getIndirizzo());
                clienteDTO.setCard(cliente.getCard());
                clienti.add(clienteDTO);
        }
        return clienti;
    }

    //post registrazione
    @RequestMapping(value = "/registrazione", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO post(@RequestBody ClienteDTO clienteDTO){
        Cliente newCliente = new Cliente();
        newCliente.setId(clienteDTO.getId());
        newCliente.setNome(clienteDTO.getNome());
        newCliente.setCognome(clienteDTO.getCognome());
        newCliente.setEmail(clienteDTO.getEmail());
        newCliente.setEta(clienteDTO.getEta());
        newCliente.setUsername(clienteDTO.getUsername());
        newCliente.setPassword(passwordEncoder().encode(clienteDTO.getPassword()));
        //newCliente.setPassword(clienteDTO.getPassword());
        newCliente.setRuolo(clienteDTO.getRuolo());
        newCliente.setIndirizzo(clienteDTO.getIndirizzo());
        newCliente.setCard(clienteDTO.getCard());

        boolean isIn = false;
        List<ClienteDTO> clienti = new ArrayList<>();
        clienti = getAll();
        for(ClienteDTO cliente: clienti) {
            if(newCliente.getUsername().equals(cliente.getUsername())) {
                isIn = true;
            }
        }

        if(isIn == false) {
            //salviamo il nuovo utente con l'id aggiornato nel database
            newCliente = clienteRepository.save(newCliente);
            //salviamo il nuovo cliente anche nel microservizio admin
            String url = "http://52.54.199.145:8080/admin/utenti/aggiungi";
            System.out.print(postApi(clienteDTO, url));
            if(clienteDTO.getRuolo().equals("cittadino")) {
                //checking, pagare
                String url1 = "http://34.193.105.215:8083/check/utenti/aggiungi";
                String url2 = "http://18.207.172.198:8082/cittadino/utenti/aggiungi";
                System.out.print(postApi(clienteDTO, url1));
                System.out.print(postApi(clienteDTO, url2));
            } else if (clienteDTO.getRuolo().equals("impiegato")) {
                //giudizio, emissione
                String url3 = "http://35.172.101.10:8085/giudizio/utenti/aggiungi";
                String url4 = "http://3.211.210.231:8086/cittadino/utenti/aggiungi";
                System.out.println(postApi(clienteDTO, url3));
                System.out.println(postApi(clienteDTO, url4));
            } else if (clienteDTO.getRuolo().equals("manager azienda rifiuti")) {
                //monitoraggio
                String url5 = "http://34.237.180.161:8084/monitorare/utenti/aggiungi";
                System.out.println(postApi(clienteDTO, url5));
            }
            System.out.println("L'Id del nuovo utente è: " + newCliente.getId());
        }
        return clienteDTO;
    }

    //post usata per aggiornare
    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO postAggiungi(@RequestBody ClienteDTO clienteDTO){
        Cliente newCliente = new Cliente();
        newCliente.setId(clienteDTO.getId());
        newCliente.setNome(clienteDTO.getNome());
        newCliente.setCognome(clienteDTO.getCognome());
        newCliente.setEmail(clienteDTO.getEmail());
        newCliente.setEta(clienteDTO.getEta());
        newCliente.setUsername(clienteDTO.getUsername());
        newCliente.setPassword(passwordEncoder().encode(clienteDTO.getPassword()));
        //newCliente.setPassword(clienteDTO.getPassword());
        newCliente.setRuolo(clienteDTO.getRuolo());
        newCliente.setIndirizzo(clienteDTO.getIndirizzo());
        newCliente.setCard(clienteDTO.getCard());

        boolean isIn = false;
        List<ClienteDTO> clienti = new ArrayList<>();
        clienti = getAll();
        for(ClienteDTO cliente: clienti) {
            if(newCliente.getUsername().equals(cliente.getUsername())) {
                isIn = true;
            }
        }

        if(isIn == false) {
            //salviamo il nuovo utente con l'id aggiornato nel database
            newCliente = clienteRepository.save(newCliente);
            System.out.println("L'Id del nuovo utente è: " + newCliente.getId());
        }
        return clienteDTO;
    }

    //api che restituisce il token
    @RequestMapping(value="/autenticazione", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        //principal è username e credentials è la password
                        loginDTO.getUsername(), loginDTO.getPassword()
                )
        );

        Cliente cliente = clienteRepository.findByUsername(authentication.getName());

        if(cliente == null) {
            throw new UsernameNotFoundException(loginDTO.getUsername());
        }

        //se rifaccio la chiamata il context si svuota perchè vale a livello di thread
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //dobbiamo generare il token
        final String jwt = jwtUtilities.generateToken(cliente.getUsername());

        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }

    public String postApi(ClienteDTO clienteDTO, String url) {
        // Creazione dell'header della richiesta
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        String jsonReq =
                "{ \"id\":" + "\"" + clienteDTO.getId() + "\"," +
                    "\"nome\":" + "\"" + clienteDTO.getNome() + "\"," +
                    "\"cognome\":" + "\"" + clienteDTO.getCognome() + "\"," +
                    "\"email\":" + "\"" + clienteDTO.getEmail() + "\"," +
                    "\"eta\":" + "\"" + clienteDTO.getEta() + "\"," +
                    "\"username\":" + "\"" + clienteDTO.getUsername() + "\"," +
                    "\"password\":" + "\"" + clienteDTO.getPassword() + "\"," +
                    "\"ruolo\":" + "\"" + clienteDTO.getRuolo() + "\"," +
                    "\"indirizzo\":" + "\"" + clienteDTO.getIndirizzo() + "\"," +
                    "\"card\":" + "\"" + clienteDTO.getCard() + "\"" +
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

    @RequestMapping(value = "/cancella", method = RequestMethod.DELETE)
    public int deleteByUsername (@RequestParam String username) {
        int result = clienteRepository.deleteByUsername(username);
        return result;
    }
}
