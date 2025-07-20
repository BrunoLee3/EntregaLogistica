package com.redekrill.entregalogistica.service;

import com.redekrill.entregalogistica.Model.Pedido;
import com.redekrill.entregalogistica.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Pedido> listPedidos(){
        return repository.findAll();
    }

    public Pedido createPedido(Pedido pedido){
        return repository.save(pedido);
    }

    public void deletePedido(Integer id){
        repository.deleteById(id);
    }
}
