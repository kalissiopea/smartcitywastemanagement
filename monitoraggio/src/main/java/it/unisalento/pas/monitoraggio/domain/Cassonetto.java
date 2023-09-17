package it.unisalento.pas.monitoraggio.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//relativo al documento del database
@Document("Cassonetto")
public class Cassonetto {
    @Id
    String id;
    String luogo;

    String tipo;

    float stato;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getStato() {
        return stato;
    }

    public void setStato(float stato) {
        this.stato = stato;
    }
}
