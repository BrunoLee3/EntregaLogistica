package com.redekrill.entregalogistica.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_faixa_horario")
    private FaixaHorario faixaHorario;

    @OneToOne
    @JoinColumn(name = "id_pedido", unique = true)
    private Pedido pedido;

    @Column(nullable = false, length = 80)
    private String fornecedor;

    @Column(nullable = false, length = 80)
    private String emailFornecedor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tipo_caminhao")
    private TipoCaminhao tipoCaminhao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tipo_paletizacao")
    private TipoPaletizacao tipoPaletizacao;

    @Column
    private String obs;
}
