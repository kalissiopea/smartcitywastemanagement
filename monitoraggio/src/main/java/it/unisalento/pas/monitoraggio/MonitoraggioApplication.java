package it.unisalento.pas.monitoraggio;

import it.unisalento.pas.monitoraggio.dto.CassonettoDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class MonitoraggioApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MonitoraggioApplication.class, args);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        String getCass = "http://localhost:8080/monitorare/cassonetti/lista";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Header-Name", "Header-Value");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(getCass, HttpMethod.GET, entity, String.class);
        String responseBody = responseEntity.getBody();
        JSONArray jsonArray = new JSONArray(responseBody);
        List<CassonettoDTO> cassonettiDTO = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            CassonettoDTO cassonettoDTO = new CassonettoDTO();
            cassonettoDTO.setId(jsonObject.getString("id"));
            cassonettoDTO.setLuogo(jsonObject.getString("luogo"));
            cassonettoDTO.setTipo(jsonObject.getString("tipo"));
            cassonettoDTO.setStato(jsonObject.getFloat("stato"));
            cassonettiDTO.add(cassonettoDTO);
        }

        for(CassonettoDTO cassonettoDTO : cassonettiDTO) {
            if(cassonettoDTO.getStato() > 100) {
                String url = "http://localhost:8080/allarme/aggiungi";
                HttpHeaders header = new HttpHeaders();
                header.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String formattedDate = sdf.format(date);

                String jsonReq =
                        "{ \"allarme\":" + "\"" + true + "\"," +
                                "\"luogo\":" + "\"" + cassonettoDTO.getLuogo() + "\"," +
                                "\"stato\":" + "\"" + cassonettoDTO.getStato() + "\"," +
                                "\"date\":" + "\"" + formattedDate + "\"" +
                                "}";

                HttpEntity<String> request = new HttpEntity<>(jsonReq, header);
                ResponseEntity<String> risposta = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            }
        }
    }

}
