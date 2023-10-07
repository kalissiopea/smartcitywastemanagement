package it.unisalento.pas.simulazione.broker;

import com.rabbitmq.client.*;
import it.unisalento.pas.simulazione.dto.CassonettoDTO;
import it.unisalento.pas.simulazione.dto.RifiutoDTO;
import it.unisalento.pas.simulazione.dto.UtenteDTO;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@Component
public class Ricevitore {

    private List<CassonettoDTO> cassonettiDTO;
    private List<UtenteDTO> utentiDTO;
    private final static String EXCHANGE_NAME = "Raccolta";
    private final String[] topic = new String[1];

    private RifiutoDTO rifiutoDTO = new RifiutoDTO();

    public Ricevitore(List<CassonettoDTO> cassonettiDTO, List<UtenteDTO> utentiDTO) {
        this.cassonettiDTO = cassonettiDTO;
        this.utentiDTO = utentiDTO;
/*        rifiutoDTO.setTipo("non reperibile");
        rifiutoDTO.setLuogo("non reperibile");
        rifiutoDTO.setUsername("non reperibile");
        rifiutoDTO.setId("non reperibile");*/
    }

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 1000))
    public CompletableFuture<RifiutoDTO> ricevere() throws IOException, TimeoutException {
        CompletableFuture<RifiutoDTO> completableFuture = new CompletableFuture<>();


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq");

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
            System.out.println(message + " " + topic[0]);
            rifiutoDTO = generareRifiuto(topic[0]);
            completableFuture.complete(rifiutoDTO);
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag ->{});
        return completableFuture;
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

        Random random3 = new Random();
        int pesoRandom = random3.nextInt(10) + 1;

        RifiutoDTO rifiutoDTO = new RifiutoDTO();
        rifiutoDTO.setUsername(usernames.get(randomIndex));
        rifiutoDTO.setLuogo(topic);
        rifiutoDTO.setTipo(tipi.get(indiceRandom));
        rifiutoDTO.setPeso(pesoRandom);
        return rifiutoDTO;
    }
}
