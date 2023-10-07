package it.unisalento.pas.giudizio.checking;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.pas.giudizio.GiudizioApplication;
import it.unisalento.pas.giudizio.domain.Checking;
import it.unisalento.pas.giudizio.repository.CheckingRepository;
import it.unisalento.pas.giudizio.restcontrollers.CheckingRestControllers;
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
@SpringBootTest(classes = GiudizioApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CheckingRestControllersTest {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    CheckingRestControllers checkingRestControllers;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(checkingRestControllers).build();
    }

    @Test
    public void testPostMethod() throws Exception {
        Checking checking = new Checking();
        checking.setId("03");
        checking.setRifiuti(1);
        checking.setPunteggio(1);
        checking.setUsername("mattiagreco");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/giudizio/check/aggiungi")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(checking)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testGetMethod() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/giudizio/check/lista"))
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

