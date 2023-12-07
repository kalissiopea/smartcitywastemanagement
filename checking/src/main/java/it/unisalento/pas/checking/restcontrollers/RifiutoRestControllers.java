package it.unisalento.pas.checking.restcontrollers;

import it.unisalento.pas.checking.domain.Rifiuto;
import it.unisalento.pas.checking.dto.RifiutoDTO;
import it.unisalento.pas.checking.repository.RifiutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("check/rifiuti/")
public class RifiutoRestControllers {

    @Autowired
    RifiutoRepository rifiutoRepository;

    //permit all
    @RequestMapping(value = "/inserisci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RifiutoDTO postInserisci(@RequestBody RifiutoDTO rifiutoDTO){
        Rifiuto newRifiuto = new Rifiuto();
        newRifiuto.setLuogo(rifiutoDTO.getLuogo());
        newRifiuto.setTipo(rifiutoDTO.getTipo());
        newRifiuto.setUsername(rifiutoDTO.getUsername());
        newRifiuto.setPeso(rifiutoDTO.getPeso());
        //salviamo il nuovo utente con l'id aggiornato nel database
        newRifiuto = rifiutoRepository.save(newRifiuto);
        return rifiutoDTO;
    }

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<RifiutoDTO> getByUsername() {
        List<Rifiuto> rifiuti = rifiutoRepository.findAll();
        List<RifiutoDTO> rifiutiDTO = new ArrayList<>();

        for(Rifiuto rifiuto : rifiuti) {
            RifiutoDTO rifiutoDTO = new RifiutoDTO();
            rifiutoDTO.setId(rifiuto.getId());
            rifiutoDTO.setUsername(rifiuto.getUsername());
            rifiutoDTO.setLuogo(rifiuto.getLuogo());
            rifiutoDTO.setTipo(rifiuto.getTipo());
            rifiutoDTO.setPeso(rifiuto.getPeso());
            rifiutiDTO.add(rifiutoDTO);
        }
        return rifiutiDTO;
    }
}
