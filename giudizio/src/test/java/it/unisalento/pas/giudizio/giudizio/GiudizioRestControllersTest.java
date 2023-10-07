package it.unisalento.pas.giudizio.giudizio;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.pas.giudizio.GiudizioApplication;
import it.unisalento.pas.giudizio.domain.Checking;
import it.unisalento.pas.giudizio.domain.Giudizio;
import it.unisalento.pas.giudizio.repository.GiudizioRepository;
import it.unisalento.pas.giudizio.restcontrollers.GiudizioRestControllers;
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

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GiudizioApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class GiudizioRestControllersTest {

    @Autowired
    GiudizioRepository giudizioRepository;

    @Autowired
    GiudizioRestControllers giudizioRestControllers;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(giudizioRestControllers).build();
    }

    @Test
    public void testPostMethod() throws Exception {
        Giudizio giudizio = new Giudizio();
        giudizio.setId("05");
        giudizio.setUsername("mattiagreco");
        giudizio.setPunteggio(1);
        giudizio.setGiudizio("Bene");
        Date date = new Date();
        giudizio.setDate(date.toString());

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/giudizio/consulta/aggiungiTest")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(giudizio)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testGetMethod() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/giudizio/consulta/lista"))
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

