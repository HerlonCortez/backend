package com.herlon.backend.web.controlers;

import com.herlon.backend.domain.entity.TransacaoReport;
import com.herlon.backend.domain.sevices.TransacaoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transacoes")
@CrossOrigin(origins = {"https://frontend-ycvu.onrender.com","http://localhost:9090"})
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransacaoReport> findAll(){
        return service.listTotaisTransacoesPorNomeLoja();
    }
}
