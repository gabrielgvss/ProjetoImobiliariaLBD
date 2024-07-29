package com.projetoLBD.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public @Data class Locacao implements EntidadeBase {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne @JoinColumn (name="id_imovel")
    private Imovel imovel;

    @ManyToOne @JoinColumn (name="id_inquilino")
    private Cliente inquilino;

    @Column (name="valor_aluguel", precision = 10, scale = 4, nullable = false)
    private BigDecimal valorAluguel;

    @Column (name="percentual_multa", precision=5, scale = 2)
    private BigDecimal percentualMulta;

    @Column (name="dia_vencimento", nullable = false)
    private Integer diaVencimento;

    @Column (name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column (name="data_fim")
    private LocalDate dataFim;

    @Column
    private boolean ativo;

    @Column
    private String obs;

    @OneToMany(mappedBy = "locacao")
    private Set<Aluguel> alugueis = new HashSet<>();

}
