package com.projetoLBD.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name="Clientes")
public @Data class Cliente implements EntidadeBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 15)
    private String cpf;

    @Column (nullable = false, length = 12)
    private String telefone;

    @Column(length = 100)
    private String email;

    @Column (name="data_nascimento")
    private LocalDate dataNascimento;

    @OneToMany (mappedBy = "proprietario")
    private Set<Imovel> imoveis = new HashSet<>();

    @OneToMany (mappedBy = "inquilino")
    private Set<Locacao> locacoes = new HashSet<>();

}
