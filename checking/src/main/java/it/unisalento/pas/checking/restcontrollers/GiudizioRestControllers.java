package it.unisalento.pas.checking.restcontrollers;

import it.unisalento.pas.checking.domain.Giudizio;
import it.unisalento.pas.checking.dto.GiudizioDTO;
import it.unisalento.pas.checking.repository.GiudizioRepository;
import it.unisalento.pas.checking.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("check/giudizio/")
public class GiudizioRestControllers {

    @Autowired
    GiudizioRepository giudizioRepository;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @RequestMapping(value = "/mie", method = RequestMethod.GET)
    public List<GiudizioDTO> getByUsername() {
        String username = getUsername();
        List<GiudizioDTO> notificheDTO = new ArrayList<>();
        for(Giudizio giudizio : giudizioRepository.findByUsername(username)) {
            GiudizioDTO giudizioDTO = new GiudizioDTO();
            giudizioDTO.setId(giudizio.getId());
            giudizioDTO.setUsername(giudizio.getUsername());
            giudizioDTO.setGiudizio(giudizio.getGiudizio());
            giudizioDTO.setPunteggio(giudizio.getPunteggio());
            giudizioDTO.setDate(giudizio.getDate());
            notificheDTO.add(giudizioDTO);
        }

        return notificheDTO;
    }

    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody GiudizioDTO giudizioDTO){
        Giudizio newGiudizio = new Giudizio();
        newGiudizio.setId(giudizioDTO.getId());
        newGiudizio.setUsername(giudizioDTO.getUsername());
        newGiudizio.setPunteggio(giudizioDTO.getPunteggio());
        newGiudizio.setGiudizio(giudizioDTO.getGiudizio());
        newGiudizio.setDate(giudizioDTO.getDate());
        newGiudizio = giudizioRepository.save(newGiudizio);
        System.out.println("L'Id nuovo è: " + newGiudizio.getId());
    }

    public String getUsername() {
        return jwtAuthenticationFilter.getUsername();
    }
}
