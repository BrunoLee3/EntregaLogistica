package com.redekrill.entregalogistica.controller;

import com.redekrill.entregalogistica.Model.Agendamento;
import com.redekrill.entregalogistica.dto.AgendamentoDTO;
import com.redekrill.entregalogistica.dto.AgendamentoResponseDTO;
import com.redekrill.entregalogistica.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> getAgendamentos(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listAgendamentos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<AgendamentoResponseDTO>> getAgendamentoById(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAgentamento(id));
    }

    @PostMapping
    public ResponseEntity<AgendamentoDTO> postAgendamento(@RequestBody @Valid AgendamentoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAgendamento(dto));

    }

    @PutMapping(path = "/{id}")
    public Agendamento putAgendamento(
            @RequestBody Agendamento agendamento,
            @PathVariable("id") Integer id
    ) {
        return service.updateAgendamento(agendamento, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAgendamento(@PathVariable("id") Integer id){
        service.deleteAgendamento(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ){
        var erros = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var nomeCampo = ((FieldError) error).getField();
                    var msgErro = error.getDefaultMessage();
                    erros.put(nomeCampo, msgErro);
                });
        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }
}
