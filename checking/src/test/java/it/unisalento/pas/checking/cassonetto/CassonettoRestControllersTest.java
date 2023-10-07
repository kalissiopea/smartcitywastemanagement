package it.unisalento.pas.checking.cassonetto;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.pas.checking.CheckingApplication;
import it.unisalento.pas.checking.domain.Cassonetto;
import it.unisalento.pas.checking.repository.CassonettoRepository;
import it.unisalento.pas.checking.restcontrollers.CassonettoRestControllers;
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
@SpringBootTest(classes = CheckingApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CassonettoRestControllersTest {

    @Autowired
    CassonettoRepository cassonettoRepository;

    @Autowired
    CassonettoRestControllers cassonettoRestControllers;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(cassonettoRestControllers).build();
    }

    @Test
    public void testPostMethod() throws Exception {
        Cassonetto cassonetto = new Cassonetto();
        cassonetto.setId("06");
        cassonetto.setLuogo("via Belice");
        cassonetto.setStato(0);
        cassonetto.setTipo("plastica");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/check/cassonetti/aggiungi")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(cassonetto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testGetMethod() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/check/cassonetti/lista"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
    }

    @Test
    public void testPutMethod() throws Exception {
        Cassonetto cassonetto = new Cassonetto();
        cassonetto.setLuogo("via Belice");
        cassonetto.setStato(5);

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/check/cassonetti/aggiornaStato/" + cassonetto.getLuogo() + "/" + cassonetto.getStato())
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
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
