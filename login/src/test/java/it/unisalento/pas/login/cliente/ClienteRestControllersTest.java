/*
package it.unisalento.pas.login.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.pas.login.LoginApplication;
import it.unisalento.pas.login.dto.ClienteDTO;
import it.unisalento.pas.login.repository.ClienteRepository;
import it.unisalento.pas.login.restcontrollers.ClienteRestControllers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoginApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ClienteRestControllersTest {

    @Autowired
    ClienteRepository utente;

    @Autowired
    ClienteRestControllers clienteRestControllers;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteRestControllers).build();
    }

    @Test
    public void testPostMethod() throws Exception {
        ClienteDTO utente = new ClienteDTO();
        utente.setId("06");
        utente.setNome("Mattia");
        utente.setCognome("Greco");
        utente.setEmail("mattiagreco@gmail.com");
        utente.setEta(30);
        utente.setUsername("mattiagreco");
        utente.setPassword("0987");
        utente.setRuolo("cittadino");
        utente.setIndirizzo("via Adriatica 5");
        utente.setCard("0987");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/utenti/aggiungi")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(utente)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testGetMethod() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/utenti/lista"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
*/
