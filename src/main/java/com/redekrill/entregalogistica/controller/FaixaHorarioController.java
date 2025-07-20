package com.redekrill.entregalogistica.controller;

import com.redekrill.entregalogistica.Model.FaixaHorario;
import com.redekrill.entregalogistica.service.FaixaHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faixas-horario")
public class FaixaHorarioController {

    private final FaixaHorarioService service;

    @Autowired
    public FaixaHorarioController(FaixaHorarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<FaixaHorario> getFaixasHorario(){
        return service.listFaixasHorario();
    }

    @PostMapping
    public FaixaHorario postFaixaHorario(@RequestBody FaixaHorario faixaHorario){
        return service.createFaixaHorario(faixaHorario);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteFaixaHorario(@PathVariable("id") Integer id){
        service.deleteFaixaHorario(id);
    }
}
