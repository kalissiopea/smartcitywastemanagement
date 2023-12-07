/*
package it.unisalento.pas.checking.rifiuto;

import it.unisalento.pas.checking.CheckingApplication;
import it.unisalento.pas.checking.repository.GiudizioRepository;
import it.unisalento.pas.checking.repository.RifiutoRepository;
import it.unisalento.pas.checking.restcontrollers.GiudizioRestControllers;
import it.unisalento.pas.checking.restcontrollers.RifiutoRestControllers;
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
@SpringBootTest(classes = CheckingApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class RifiutoRestControllersTest {

    @Autowired
    RifiutoRepository rifiutoRepository;

    @Autowired
    RifiutoRestControllers rifiutoRestControllers;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(rifiutoRestControllers).build();
    }

    @Test
    public void testGetMethod() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/check/rifiuti/lista"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
    }

}
*/
