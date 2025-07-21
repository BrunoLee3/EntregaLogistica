package com.redekrill.entregalogistica.controller;

import com.redekrill.entregalogistica.Model.Pedido;
import com.redekrill.entregalogistica.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll(){
        return ResponseEntity.ok(service.listPedidos());
    }

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido){
        Pedido created = service.createPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}
