package it.unisalento.pas.pagare.restcontrollers;

import it.unisalento.pas.pagare.domain.Tassa;
import it.unisalento.pas.pagare.dto.TassaDTO;
import it.unisalento.pas.pagare.repository.TassaRepository;
import it.unisalento.pas.pagare.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("cittadino/tassa")
public class TassaRestControllers {

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    TassaRepository tassaRepository;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<TassaDTO> getAll() {
        List<TassaDTO> tasse = new ArrayList<>();

        for (Tassa tassa : tassaRepository.findAll()){
            TassaDTO tassaDTO = new TassaDTO();
            tassaDTO.setId(tassa.getId());
            tassaDTO.setData(tassa.getData());
            tassaDTO.setImporto(tassa.getImporto());
            tassaDTO.setUsername(tassa.getUsername());

            tasse.add(tassaDTO);
        }
        return tasse;
    }

    @RequestMapping(value = "/mie",method = RequestMethod.GET)
    public List<TassaDTO> findByUsername(){
        String username = getUsername();
        System.out.println(username);
        List<Tassa> result= tassaRepository.findByUsername(username);
        List<TassaDTO> tasseDTO = new ArrayList<>();

        for (Tassa tassa : result){
            TassaDTO tassaDTO = new TassaDTO();
            tassaDTO.setId(tassa.getId());
            tassaDTO.setData(tassa.getData());
            tassaDTO.setImporto(tassa.getImporto());
            tassaDTO.setUsername(tassa.getUsername());
            tasseDTO.add(tassaDTO);
        }
        return tasseDTO;
    }

    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody TassaDTO tassaDTO){
        Tassa newTassa = new Tassa();
        newTassa.setId(tassaDTO.getId());
        newTassa.setUsername(tassaDTO.getUsername());
        newTassa.setImporto(tassaDTO.getImporto());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = sdf.format(date);
        newTassa.setData(formattedDate);
        newTassa = tassaRepository.save(newTassa);
        System.out.println("L'Id nuovo è: " + newTassa.getId());
    }

    public String getUsername() {
        return jwtAuthenticationFilter.getUsername();
    }

}
