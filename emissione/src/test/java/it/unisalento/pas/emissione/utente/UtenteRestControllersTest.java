package it.unisalento.pas.emissione.utente;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.pas.emissione.EmissioneApplication;
import it.unisalento.pas.emissione.domain.Utente;
import it.unisalento.pas.emissione.repository.UtenteRepository;
import it.unisalento.pas.emissione.restcontrollers.UtenteRestControllers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmissioneApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UtenteRestControllersTest {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    UtenteRestControllers utenteRestControllers;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(utenteRestControllers).build();
    }

    @Test
    public void testPostMethod() throws Exception {
        Utente utente = new Utente();
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

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/cittadino/utenti/aggiungi")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(utente)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testGetMethod() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/cittadino/utenti/lista"))
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
