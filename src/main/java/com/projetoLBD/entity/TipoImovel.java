package com.projetoLBD.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "Tipo_Imovel")
public @Data class TipoImovel implements EntidadeBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 256)
    private String descricao;

    @OneToMany(mappedBy = "tipoImovel")
    private Set<Imovel> imoveis = new HashSet<>();
}
