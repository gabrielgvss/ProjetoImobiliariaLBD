package com.projetoLBD.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity @Table(name="Servicos_Imovel")
public @Data class ServicoImovel implements EntidadeBase{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne @JoinColumn(name="id_profissional")
    private Profissional profissional;

    @ManyToOne @JoinColumn(name="id_imovel")
    private Imovel imovel;

    @Column(name="data_servico")
    private LocalDate dataServico;

    @Column (name="valor_total", precision = 10, scale=2)
    private BigDecimal valorTotal;

    @Column
    private String obs;
}
