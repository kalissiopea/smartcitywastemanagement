package it.unisalento.pas.simulazione.broker;

import com.rabbitmq.client.*;
import it.unisalento.pas.simulazione.dto.CassonettoDTO;
import it.unisalento.pas.simulazione.dto.RifiutoDTO;
import it.unisalento.pas.simulazione.dto.UtenteDTO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class Ricevitore {

    private List<CassonettoDTO> cassonettiDTO;
    private List<UtenteDTO> utentiDTO;
    private final static String EXCHANGE_NAME = "Raccolta";
    private final String[] topic = new String[1];

    public Ricevitore(List<CassonettoDTO> cassonettiDTO, List<UtenteDTO> utentiDTO) {
        this.cassonettiDTO = cassonettiDTO;
        this.utentiDTO = utentiDTO;
    }

    public RifiutoDTO ricevere() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String queueName = channel.queueDeclare().getQueue();

        List<String> routingKeys = generateRoutingKey();
        for(String routingKey : routingKeys ) {
            channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
        }

        System.out.println("In attesa di messaggi. CTRL-C per terminare.");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            topic[0] = delivery.getEnvelope().getRoutingKey();
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(message);
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag ->{});
        RifiutoDTO rifiutoDTO = generareRifiuto(topic[0]);
        return rifiutoDTO;
    }

    public List<String> generateRoutingKey() {
        List<String> topics = new ArrayList<>();
        for(CassonettoDTO cassonettoDTO : cassonettiDTO) {
            topics.add(cassonettoDTO.getLuogo());
        }
        return topics;
    }

    public RifiutoDTO generareRifiuto(String topic){
        List<String> usernames = new ArrayList<>();
        for(UtenteDTO utenteDTO : utentiDTO) {
            usernames.add(utenteDTO.getUsername());
        }

        Random random1 = new Random();
        int randomIndex = random1.nextInt(usernames.size());

        List<String> tipi = new ArrayList<>();
        tipi.add("plastica");
        tipi.add("vetro");
        tipi.add("indifferenziata");
        tipi.add("organico");
        tipi.add("carta");

        Random random2 = new Random();
        int indiceRandom = random2.nextInt(tipi.size());

        RifiutoDTO rifiutoDTO = new RifiutoDTO();
        rifiutoDTO.setUsername(usernames.get(randomIndex));
        rifiutoDTO.setLuogo(topic);
        rifiutoDTO.setTipo(tipi.get(indiceRandom));
        return rifiutoDTO;
    }
}
