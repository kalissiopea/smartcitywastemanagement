/*
package it.unisalento.pas.emissione.checking;

import it.unisalento.pas.emissione.EmissioneApplication;
import it.unisalento.pas.emissione.repository.CheckingRepository;
import it.unisalento.pas.emissione.restcontrollers.CheckingRestControllers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    public void testGetMethod() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/cittadino/check/lista"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
    }
}
*/
