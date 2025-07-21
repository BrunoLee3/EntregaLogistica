package com.redekrill.entregalogistica.controller;

import com.redekrill.entregalogistica.Model.TipoPaletizacao;
import com.redekrill.entregalogistica.service.TipoPaletizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TipoPaletizacao>> getAll(){
        return ResponseEntity.ok(service.getPaletizacao());
    }

    @PostMapping
    public ResponseEntity<TipoPaletizacao> create(@RequestBody TipoPaletizacao tipoPaletizacao){
        TipoPaletizacao created = service.createPaletizacao(tipoPaletizacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.deletePaletizacao(id);
        return ResponseEntity.noContent().build();
    }
}
