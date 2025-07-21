package com.redekrill.entregalogistica.controller;

import com.redekrill.entregalogistica.Model.Agendamento;
import com.redekrill.entregalogistica.dto.AgendamentoDTO;
import com.redekrill.entregalogistica.dto.AgendamentoResponseDTO;
import com.redekrill.entregalogistica.service.AgendamentoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> getAll() {
        return ResponseEntity.ok(service.listAgendamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> getById(@PathVariable Integer id) {
        return service.findAgentamento(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AgendamentoDTO> create(@RequestBody @Valid AgendamentoDTO dto) {
        AgendamentoDTO created = service.createAgendamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> update(@PathVariable Integer id, @RequestBody @Valid Agendamento agendamento) {
        try {
            Agendamento updated = service.updateAgendamento(agendamento, id);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        var erros = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            var nomeCampo = ((FieldError) error).getField();
            var msgErro = error.getDefaultMessage();
            erros.put(nomeCampo, msgErro);
        });
        return ResponseEntity.badRequest().body(erros);
    }
}
