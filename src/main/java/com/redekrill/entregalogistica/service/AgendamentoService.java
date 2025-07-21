package com.redekrill.entregalogistica.service;

import com.redekrill.entregalogistica.Model.Agendamento;
import com.redekrill.entregalogistica.Model.FaixaHorario;
import com.redekrill.entregalogistica.dto.AgendamentoDTO;
import com.redekrill.entregalogistica.dto.AgendamentoResponseDTO;
import com.redekrill.entregalogistica.repository.AgendamentoRepository;
import com.redekrill.entregalogistica.repository.FaixaHorarioRepository;
import com.redekrill.entregalogistica.repository.PedidoRepository;
import com.redekrill.entregalogistica.repository.TipoCaminhaoRepository;
import com.redekrill.entregalogistica.repository.TipoPaletizacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private int limitePaletesDia = 480;
    private int limitePaletesFaixaHoraria = 120;

    private final AgendamentoRepository repository;
    private final AgendamentoMapper mapper;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FaixaHorarioRepository faixaHorarioRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private TipoCaminhaoRepository tipoCaminhaoRepository;
    @Autowired
    private TipoPaletizacaoRepository tipoPaletizacaoRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository repository, AgendamentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AgendamentoResponseDTO> listAgendamentos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .sorted(Comparator
                        .comparing(AgendamentoResponseDTO::data)
                        .thenComparing(dto -> LocalTime.parse(dto.faixaHorario().substring(0, 5)))
                )
                .collect(Collectors.toList());
    }

    public Optional<AgendamentoResponseDTO> findAgentamento(Integer id){
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    public AgendamentoDTO createAgendamento(AgendamentoDTO dto){
        var agendamento = mapper.toEntity(dto);
        agendamento.setFaixaHorario(faixaHorarioRepository.findById(dto.IdfaixaHorario()).orElse(null));
        agendamento.setPedido(pedidoRepository.findById(dto.idPedido()).orElse(null));
        agendamento.setTipoCaminhao(tipoCaminhaoRepository.findById(dto.idTipoCaminhao()).orElse(null));
        agendamento.setTipoPaletizacao(tipoPaletizacaoRepository.findById(dto.idTipoPaletizacao()).orElse(null));

        int totalPaletesData = calculaTotalPaletesDoDia(agendamento.getData(), agendamento.getPedido().getQtdPaletes());
        int totalPaletesFaixaHoraria = calculaTotalPaletesFaixaHora(agendamento.getFaixaHorario(), agendamento.getPedido().getQtdPaletes());

        if (totalPaletesData > limitePaletesDia) {
            System.out.println("Limite de 480 paletes por dia excedido");
        }

        if (totalPaletesFaixaHoraria > limitePaletesFaixaHoraria) {
            System.out.println("Limite de 120 paletes por faixa horária");
        }

        repository.save(agendamento);
        enviarEmailFornecedor(agendamento);
        return dto;
    }

    public Agendamento updateAgendamento(Agendamento agendamento, Integer id){
        return repository.findById(id)
                .map(agendamentoExistente -> {
                    agendamento.setId(id);
                    return repository.save(agendamento);
                })
                .orElseThrow(() -> new EntityNotFoundException("Angendamento não encontrado"));
    }

    public void deleteAgendamento(Integer id){
        repository.deleteById(id);
    }

    private int calculaTotalPaletesDoDia(LocalDate data, int paletesNovos) {
        List<Agendamento> agendamentos = repository.findByData(data);
        int paletesExistentes = agendamentos.stream()
                .mapToInt(a -> a.getPedido().getQtdPaletes())
                .sum();
        return paletesExistentes + paletesNovos;
    }

    private int calculaTotalPaletesFaixaHora(FaixaHorario faixaHorario, int paletesNovos){
        List<Agendamento> agendamentos = repository.findByFaixaHorario(faixaHorario);
        int paletesExistentes = agendamentos.stream()
                .mapToInt(a -> a.getPedido().getQtdPaletes())
                .sum();
        return paletesExistentes + paletesNovos;
    }

    private void enviarEmailFornecedor(Agendamento agendamento) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("no-reply@ethereal.email");
            helper.setTo(agendamento.getEmailFornecedor());
            helper.setSubject("Agendamento cadastrado com sucesso");
            StringBuilder conteudo = new StringBuilder();
            conteudo.append("Olá, seu agendamento foi cadastrado com sucesso!\n\n");
            conteudo.append("Data: ").append(agendamento.getData()).append("\n");
            conteudo.append("Faixa Horária: ").append(agendamento.getFaixaHorario().getDescricao()).append("\n");
            conteudo.append("Fornecedor: ").append(agendamento.getFornecedor()).append("\n");
            conteudo.append("E-mail do Fornecedor: ").append(agendamento.getEmailFornecedor()).append("\n");
            conteudo.append("Tipo de Caminhão: ").append(agendamento.getTipoCaminhao().getTipo()).append("\n");
            conteudo.append("Tipo de Paletização: ").append(agendamento.getTipoPaletizacao().getTipo()).append("\n");
            conteudo.append("Quantidade de Paletes: ").append(agendamento.getPedido().getQtdPaletes()).append("\n");
            if (agendamento.getObs() != null && !agendamento.getObs().isEmpty()) {
                conteudo.append("Observação: ").append(agendamento.getObs()).append("\n");
            }
            helper.setText(conteudo.toString(), false);
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar email: " + e.getMessage());
        }
    }
}
