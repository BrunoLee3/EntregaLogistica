package com.redekrill.entregalogistica.controller;

import com.redekrill.entregalogistica.Model.FaixaHorario;
import com.redekrill.entregalogistica.service.FaixaHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<FaixaHorario>> getAll(){
        return ResponseEntity.ok(service.listFaixasHorario());
    }

    @PostMapping
    public ResponseEntity<FaixaHorario> create(@RequestBody FaixaHorario faixaHorario){
        FaixaHorario created = service.createFaixaHorario(faixaHorario);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.deleteFaixaHorario(id);
        return ResponseEntity.noContent().build();
    }
}
