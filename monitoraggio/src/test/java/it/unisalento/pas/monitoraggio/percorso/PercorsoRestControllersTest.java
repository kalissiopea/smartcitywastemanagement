package it.unisalento.pas.monitoraggio.percorso;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.pas.monitoraggio.MonitoraggioApplication;
import it.unisalento.pas.monitoraggio.domain.Percorso;
import it.unisalento.pas.monitoraggio.repository.PercorsoRepository;
import it.unisalento.pas.monitoraggio.restcontrollers.PercorsoRestControllers;
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
public class PercorsoRestControllersTest {

    @Autowired
    PercorsoRepository percorsoRepository;

    @Autowired
    PercorsoRestControllers percorsoRestControllers;

    private MockMvc mockMvc;

    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String formattedDate = sdf.format(date);

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(percorsoRestControllers).build();
    }

    @Test
    public void testGetMethod() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/percorso/lista"))
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
