package com.redekrill.entregalogistica.controller;

import com.redekrill.entregalogistica.Model.Pedido;
import com.redekrill.entregalogistica.service.PedidoService;
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
    public List<Pedido> getPedidos(){
        return service.listPedidos();
    }

    @PostMapping
    public Pedido postPedido(@RequestBody Pedido pedido){
        return service.createPedido(pedido);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePedido(@PathVariable("id") Integer id){
        service.deletePedido(id);
    }
}
