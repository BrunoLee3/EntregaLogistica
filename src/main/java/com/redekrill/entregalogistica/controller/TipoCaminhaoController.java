package com.redekrill.entregalogistica.controller;

import com.redekrill.entregalogistica.Model.TipoCaminhao;
import com.redekrill.entregalogistica.service.TipoCaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TipoCaminhao> getTiposCaminhao(){
        return service.listTiposCaminhao();
    }

    @PostMapping
    public TipoCaminhao postTipoCaminhao(@RequestBody TipoCaminhao tipoCaminhao){
        return service.createTipoCaminhao(tipoCaminhao);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCaminhao(@PathVariable("id") Integer id){
        service.deleteTipoCaminhao(id);
    }
}
