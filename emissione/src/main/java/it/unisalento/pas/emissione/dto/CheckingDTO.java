package it.unisalento.pas.emissione.dto;

public class CheckingDTO {
    String id;
    private String username;
    private int rifiuti;
    private int punteggio;

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
