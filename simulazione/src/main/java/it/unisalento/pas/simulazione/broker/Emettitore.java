package it.unisalento.pas.simulazione.broker;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import it.unisalento.pas.simulazione.dto.CassonettoDTO;
import it.unisalento.pas.simulazione.dto.RifiutoDTO;
import it.unisalento.pas.simulazione.dto.UtenteDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

@Component
public class Emettitore {

    private List<CassonettoDTO> cassonettiDTO;

    private final static String EXCHANGE_NAME = "Raccolta";

    public Emettitore(List<CassonettoDTO> cassonettiDTO) {
        this.cassonettiDTO = cassonettiDTO;
    }

    public void emettere() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq");

        try(Connection connection = factory.newConnection()){
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            List<String> topics = generateRoutingKey();
            Random random = new Random();
            int randomIndex = random.nextInt(topics.size());
            String selectedTopic = topics.get(randomIndex);
            channel.basicPublish(EXCHANGE_NAME, selectedTopic, null, "dove hai pubblicato?".getBytes(StandardCharsets.UTF_8));
        }
    }

    public List<String> generateRoutingKey() {
        //get cassonetti
        List<String> topics = new ArrayList<>();
        for(CassonettoDTO cassonettoDTO : cassonettiDTO) {
            topics.add(cassonettoDTO.getLuogo());
        }
        return topics;
    }
}
