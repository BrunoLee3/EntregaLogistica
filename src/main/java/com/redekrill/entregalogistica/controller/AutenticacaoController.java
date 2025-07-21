package com.redekrill.entregalogistica.controller;

import com.redekrill.entregalogistica.Model.Usuario;
import com.redekrill.entregalogistica.dto.AutenticacaoDTO;
import com.redekrill.entregalogistica.dto.CadastroDTO;
import com.redekrill.entregalogistica.dto.LoginResponseDTO;
import com.redekrill.entregalogistica.infra.security.TokenService;
import com.redekrill.entregalogistica.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final TokenService tokenService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, UsuarioRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AutenticacaoDTO dto){
        var senhaUsuario = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = authenticationManager.authenticate(senhaUsuario);
        var token = tokenService.geraToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastro(@RequestBody @Valid CadastroDTO dto){
        if(repository.findByEmail(dto.email()) != null){
            return ResponseEntity.badRequest().build();
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        Usuario novoUsuario = new Usuario(dto.email(), senhaCriptografada, dto.cargo());
        repository.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
