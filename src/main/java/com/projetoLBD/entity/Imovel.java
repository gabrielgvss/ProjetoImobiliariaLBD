package com.projetoLBD.entity;

import lombok.Data;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name="Imoveis")
public @Data class Imovel implements EntidadeBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne @JoinColumn(name="id_tipo_imovel")
    private TipoImovel tipoImovel;

    @ManyToOne @JoinColumn(name = "id_proprietario")
    private Cliente proprietario;

    @Column (length = 200, nullable = false)
    private String logradouro;

    @Column (length = 45, nullable = false)
    private String bairro;

    @Column (length = 10, nullable = false)
    private String cep;

    @Column
    private Integer metragem;

    @Column
    private Integer dormitorios;

    @Column
    private Integer banheiros;

    @Column
    private Integer suites;

    @Column(name = "vagas_garagem")
    private Integer vagasGaragem;

    @Column(name = "valor_aluguel_sugerido", precision = 10, scale = 2)
    private BigDecimal valorAluguelSugerido;

    @Column
    private String obs;

    @OneToMany(mappedBy = "imovel")
    private Set<Locacao> locacoes = new HashSet<>();

    @OneToMany (mappedBy = "imovel")
    private Set<ServicoImovel> servicos = new HashSet<>();

}
