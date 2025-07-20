package com.redekrill.entregalogistica.service;

import com.redekrill.entregalogistica.Model.FaixaHorario;
import com.redekrill.entregalogistica.repository.FaixaHorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaixaHorarioService {

    private final FaixaHorarioRepository repository;

    @Autowired
    public FaixaHorarioService(FaixaHorarioRepository repository) {
        this.repository = repository;
    }

    public List<FaixaHorario> listFaixasHorario(){
        return repository.findAll();
    }

    public FaixaHorario createFaixaHorario(FaixaHorario faixaHorario){
        return repository.save(faixaHorario);
    }

    public void deleteFaixaHorario(Integer id){
        repository.deleteById(id);
    }
}
