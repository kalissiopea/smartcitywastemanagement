package it.unisalento.pas.monitoraggio.dto;

public class AllarmeDTO {

    String id;
    private boolean allarme;
    private String luogo;
    private float stato;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAllarme() {
        return allarme;
    }

    public void setAllarme(boolean allarme) {
        this.allarme = allarme;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public float getStato() {
        return stato;
    }

    public void setStato(float stato) {
        this.stato = stato;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
