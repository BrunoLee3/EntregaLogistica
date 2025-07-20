package com.redekrill.entregalogistica.service;

import com.redekrill.entregalogistica.Model.TipoPaletizacao;
import com.redekrill.entregalogistica.repository.TipoPaletizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPaletizacaoService {

    private final TipoPaletizacaoRepository repository;

    @Autowired
    public TipoPaletizacaoService(TipoPaletizacaoRepository tipoPaletizacaoRepository) {
        this.repository = tipoPaletizacaoRepository;
    }

    public List<TipoPaletizacao> getPaletizacao(){
        return repository.findAll();
    }

    public void createPaletizacao(TipoPaletizacao tipoPaletizacao){
        repository.save(tipoPaletizacao);
    }
}
