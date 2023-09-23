package it.unisalento.pas.emissione.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Tassa")
public class Tassa {
    @Id
    String id;
    String username;
    String data;
    Float importo;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Float getImporto() {
        return importo;
    }

    public void setImporto(Float importo) {
        this.importo = importo;
    }
}
