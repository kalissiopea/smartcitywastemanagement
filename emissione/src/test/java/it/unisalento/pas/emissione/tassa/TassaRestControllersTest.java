package it.unisalento.pas.emissione.tassa;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.pas.emissione.EmissioneApplication;
import it.unisalento.pas.emissione.domain.Tassa;
import it.unisalento.pas.emissione.repository.TassaRepository;
import it.unisalento.pas.emissione.restcontrollers.TassaRestControllers;
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
@SpringBootTest(classes = EmissioneApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TassaRestControllersTest {

    @Autowired
    TassaRepository tassaRepository;

    @Autowired
    TassaRestControllers tassaRestControllers;

    private MockMvc mockMvc;

    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String formattedDate = sdf.format(date);

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(tassaRestControllers).build();
    }

    @Test
    public void testGetMethod() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/cittadino/tassa/lista"))
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

