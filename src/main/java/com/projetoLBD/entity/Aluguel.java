package com.projetoLBD.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity @Table(name="Alugueis")
public @Data class Aluguel implements EntidadeBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne @JoinColumn(name="id_locacao")
    private Locacao locacao;

    @Column (name="data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column (name="valor_pago", precision = 10, scale = 2)
    private BigDecimal valorPago;

    @Column (name="data_pagamento")
    private LocalDate dataPagamento;

    @Column
    private String obs;

}
