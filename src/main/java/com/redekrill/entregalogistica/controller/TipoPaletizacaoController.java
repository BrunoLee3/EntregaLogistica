package com.redekrill.entregalogistica.controller;

import com.redekrill.entregalogistica.Model.TipoPaletizacao;
import com.redekrill.entregalogistica.service.TipoPaletizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/tipos-paletizacao")
public class TipoPaletizacaoController {

    private final TipoPaletizacaoService service;

    @Autowired
    public TipoPaletizacaoController(TipoPaletizacaoService tipoPaletizacaoService) {
        this.service = tipoPaletizacaoService;
    }

    @GetMapping
    public List<TipoPaletizacao> getPaletizacao(){
        return service.getPaletizacao();
    }

    @PostMapping
    public void postPaletizacao(@RequestBody TipoPaletizacao tipoPaletizacao){
        service.createPaletizacao(tipoPaletizacao);
    }
}
