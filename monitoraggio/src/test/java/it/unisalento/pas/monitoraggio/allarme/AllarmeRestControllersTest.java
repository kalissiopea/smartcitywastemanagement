/*
package it.unisalento.pas.monitoraggio.allarme;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.pas.monitoraggio.MonitoraggioApplication;
import it.unisalento.pas.monitoraggio.domain.Allarme;
import it.unisalento.pas.monitoraggio.repository.AllarmeRepository;
import it.unisalento.pas.monitoraggio.restcontrollers.AllarmeRestControllers;
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

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitoraggioApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class AllarmeRestControllersTest {

    @Autowired
    AllarmeRepository allarmeRepository;

    @Autowired
    AllarmeRestControllers allarmeRestControllers;

    private MockMvc mockMvc;

    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String formattedDate = sdf.format(date);

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(allarmeRestControllers).build();
    }

    @Test
    public void testPostMethod() throws Exception {
        Allarme allarme = new Allarme();
        allarme.setId("20");
        allarme.setLuogo("via Belice");
        allarme.setAllarme(true);
        allarme.setStato(0);
        allarme.setDate(formattedDate);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/allarme/aggiungi")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(allarme)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testGetMethod() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/allarme/lista"))
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
