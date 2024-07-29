package com.projetoLBD.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name="Profissionais")
public @Data class Profissional implements EntidadeBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String profissao;

    @Column(nullable = false, length = 12)
    private String telefone1;

    @Column(length = 12)
    private String telefone2;

    @Column(name="valor_hora", precision=10, scale=2)
    private BigDecimal valorHora;

    @Column
    private String obs;

    @OneToMany(mappedBy = "profissional")
    private Set<ServicoImovel> servicos = new HashSet<>();

}
