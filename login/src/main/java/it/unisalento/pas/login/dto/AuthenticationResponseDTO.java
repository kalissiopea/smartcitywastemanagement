package it.unisalento.pas.login.dto;

public class AuthenticationResponseDTO {
    private String jwt;

    private String ruolo;

    public AuthenticationResponseDTO(String jwt, String ruolo) {
        this.jwt = jwt;
        this.ruolo = ruolo;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
