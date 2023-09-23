package it.unisalento.pas.pagare.restcontrollers;

import it.unisalento.pas.pagare.domain.Pagamento;
import it.unisalento.pas.pagare.dto.PagamentoDTO;
import it.unisalento.pas.pagare.repository.PagamentoRepository;
import it.unisalento.pas.pagare.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("cittadino/pagamenti")
public class PagamentoRestControllers {

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public List<PagamentoDTO> getAll() {
        List<PagamentoDTO> pagamenti = new ArrayList<>();

        for (Pagamento pagamento : pagamentoRepository.findAll()){
            PagamentoDTO pagamentoDTO = new PagamentoDTO();
            pagamentoDTO.setId(pagamento.getId());
            pagamentoDTO.setData(pagamento.getData());
            pagamentoDTO.setCosto(pagamento.getCosto());
            pagamentoDTO.setCard(pagamento.getCard());
            pagamentoDTO.setUsername(pagamento.getUsername());
            pagamentoDTO.setStato(pagamento.getStato());

            pagamenti.add(pagamentoDTO);
        }
        return pagamenti;

    }

    @RequestMapping(value = "/stato",method = RequestMethod.GET)
    public List<PagamentoDTO> findByStato(@RequestParam Boolean stato){
        List<Pagamento> result= pagamentoRepository.findByStato(stato);
        List<PagamentoDTO> pagamenti = new ArrayList<>();

        for (Pagamento pagamento : result){
            PagamentoDTO pagamentoDTO = new PagamentoDTO();
            pagamentoDTO.setId(pagamento.getId());
            pagamentoDTO.setData(pagamento.getData());
            pagamentoDTO.setCosto(pagamento.getCosto());
            pagamentoDTO.setCard(pagamento.getCard());
            pagamentoDTO.setUsername(pagamento.getUsername());
            pagamentoDTO.setStato(pagamento.getStato());
            pagamenti.add(pagamentoDTO);
        }
        return pagamenti;
    }

    @RequestMapping(value = "/mie",method = RequestMethod.GET)
    public List<PagamentoDTO> findByUsername(){
        String username = getUsername();
        List<Pagamento> result= pagamentoRepository.findByUsername(username);
        List<PagamentoDTO> pagamenti = new ArrayList<>();

        for (Pagamento pagamento : result){
            PagamentoDTO pagamentoDTO = new PagamentoDTO();
            pagamentoDTO.setId(pagamento.getId());
            pagamentoDTO.setData(pagamento.getData());
            pagamentoDTO.setCosto(pagamento.getCosto());
            pagamentoDTO.setCard(pagamento.getCard());
            pagamentoDTO.setUsername(pagamento.getUsername());
            pagamentoDTO.setStato(pagamento.getStato());
            pagamenti.add(pagamentoDTO);
        }
        return pagamenti;
    }

    @RequestMapping(value = "/aggiungi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody PagamentoDTO pagamentoDTO){
        Pagamento newPagamento = new Pagamento();
        newPagamento.setId(pagamentoDTO.getId());
        newPagamento.setUsername(pagamentoDTO.getUsername());
        newPagamento.setStato(pagamentoDTO.getStato());
        newPagamento.setCosto(pagamentoDTO.getCosto());
        newPagamento.setCard(pagamentoDTO.getCard());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = sdf.format(date);
        newPagamento.setData(formattedDate);
        newPagamento = pagamentoRepository.save(newPagamento);
        System.out.println("L'Id nuovo è: " + newPagamento.getId());
    }

    public String getUsername() {
        return jwtAuthenticationFilter.getUsername();
    }
}
