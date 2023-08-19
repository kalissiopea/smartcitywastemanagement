package it.unisalento.pas.admin.dto;

//cassonetto effettivo
public class CassonettoDTO {
    String id;
    private String luogo;
    private float stato;

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

    public float getStato() {
        return stato;
    }

    public void setStato(float stato) {
        this.stato = stato;
    }
}
