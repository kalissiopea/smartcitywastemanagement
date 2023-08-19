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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

        List<Cliente> altriUtenti = getApiResponse();
        List<Cliente> questiClienti = clienteRepository.findAll();
        List<Cliente> tuttiUtenti = new ArrayList<>(altriUtenti);
        tuttiUtenti.addAll(questiClienti);

        for(Cliente cliente : tuttiUtenti) {
        //for(Cliente cliente : clienteRepository.findAll()) {
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

    public List<Cliente> getApiResponse() {
        String url = "http://admin:8080/admin/utenti/lista";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Header-Name", "Header-Value");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = response.getBody();

        JSONArray jsonArray = new JSONArray(responseBody);
        List<Cliente> clienti = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Cliente cliente = new Cliente();
            cliente.setId(jsonObject.getString("id"));
            cliente.setNome(jsonObject.getString("nome"));
            cliente.setCognome(jsonObject.getString("cognome"));
            cliente.setEmail(jsonObject.getString("email"));
            cliente.setEta(jsonObject.getInt("eta"));
            cliente.setUsername(jsonObject.getString("username"));
            cliente.setRuolo(jsonObject.getString("ruolo"));
            cliente.setIndirizzo("null");
            cliente.setCard("null");
            clienti.add(cliente);
        }

        return clienti;
    }
}
