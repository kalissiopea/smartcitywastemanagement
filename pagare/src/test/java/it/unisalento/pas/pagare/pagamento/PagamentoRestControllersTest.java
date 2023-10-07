package it.unisalento.pas.pagare.pagamento;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.pas.pagare.PagareApplication;
import it.unisalento.pas.pagare.domain.Pagamento;
import it.unisalento.pas.pagare.repository.PagamentoRepository;
import it.unisalento.pas.pagare.restcontrollers.PagamentoRestControllers;
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
@SpringBootTest(classes = PagareApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PagamentoRestControllersTest {

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    PagamentoRestControllers pagamentoRestControllers;

    private MockMvc mockMvc;

    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String formattedDate = sdf.format(date);

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoRestControllers).build();
    }

    @Test
    public void testPostMethod() throws Exception {
        Pagamento pagamento = new Pagamento();
        pagamento.setId("20");
        pagamento.setUsername("mattiagreco");
        pagamento.setStato(true);
        pagamento.setCosto(100.0f);
        pagamento.setData(formattedDate);
        pagamento.setCard("0987");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/cittadino/pagamenti/aggiungi")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(pagamento)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void testGetMethod() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/cittadino/pagamenti/lista"))
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
