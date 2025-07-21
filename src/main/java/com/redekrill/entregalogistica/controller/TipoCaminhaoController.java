package com.redekrill.entregalogistica.controller;

import com.redekrill.entregalogistica.Model.TipoCaminhao;
import com.redekrill.entregalogistica.service.TipoCaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/tipos-caminhao")
public class TipoCaminhaoController {

    private final TipoCaminhaoService service;

    @Autowired
    public TipoCaminhaoController(TipoCaminhaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoCaminhao>> getAll(){
        return ResponseEntity.ok(service.listTiposCaminhao());
    }

    @PostMapping
    public ResponseEntity<TipoCaminhao> create(@RequestBody TipoCaminhao tipoCaminhao){
        TipoCaminhao created = service.createTipoCaminhao(tipoCaminhao);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.deleteTipoCaminhao(id);
        return ResponseEntity.noContent().build();
    }
}
