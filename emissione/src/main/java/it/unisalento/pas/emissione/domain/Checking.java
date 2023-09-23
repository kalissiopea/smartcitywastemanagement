package it.unisalento.pas.emissione.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Checking")
public class Checking {
    @Id
    String id;
    String username;
    int rifiuti;
    int punteggio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRifiuti() {
        return rifiuti;
    }

    public void setRifiuti(int rifiuti) {
        this.rifiuti = rifiuti;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }
}
