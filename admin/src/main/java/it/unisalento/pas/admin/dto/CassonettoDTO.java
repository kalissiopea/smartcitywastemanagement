package it.unisalento.pas.admin.dto;

//cassonetto effettivo
public class CassonettoDTO {
    String id;
    private String luogo;
    private String tipo;
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
