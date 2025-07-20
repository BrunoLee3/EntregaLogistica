package com.redekrill.entregalogistica.service;

import com.redekrill.entregalogistica.Model.TipoCaminhao;
import com.redekrill.entregalogistica.repository.TipoCaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoCaminhaoService {

    private final TipoCaminhaoRepository repository;

    @Autowired
    public TipoCaminhaoService(TipoCaminhaoRepository tipoCaminhaoRepository) {
        this.repository = tipoCaminhaoRepository;
    }

    public List<TipoCaminhao> listTiposCaminhao(){
        return repository.findAll();
    }

    public TipoCaminhao createTipoCaminhao(TipoCaminhao tipoCaminhao){
        return repository.save(tipoCaminhao);
    }

    public void deleteTipoCaminhao(Integer id){
        repository.deleteById(id);
    }
}
