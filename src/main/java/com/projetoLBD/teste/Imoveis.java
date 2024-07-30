package com.projetoLBD.teste;

import com.projetoLBD.entity.*;
import com.projetoLBD.repository.AluguelRepository;
import com.projetoLBD.repository.ImovelRepository;
import com.projetoLBD.repository.LocacaoRepository;
import com.projetoLBD.repository.ServicoImovelRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Imoveis {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("imobiliaria_pu");
        EntityManager em = null;

        try {
            em = emf.createEntityManager();
            AluguelRepository alugueisRepo = new AluguelRepository(em);
            ImovelRepository imoveisRepo = new ImovelRepository(em);
            LocacaoRepository locacaoRepo = new LocacaoRepository(em);

            em.getTransaction().begin();

            // Instanciação dos objetos necessários
            Cliente cliente = new Cliente();
            cliente.setNome("Victor José");
            cliente.setCpf("125945100");
            cliente.setTelefone("9989515199");
            cliente.setEmail("victorjose@example.com");
            cliente.setDataNascimento(LocalDate.from(LocalDate.of(1999, 7, 25)));
            em.persist(cliente);

            Imovel imovelAlugado = new Imovel();
            imovelAlugado.setLogradouro("Rua Teste");
            imovelAlugado.setBairro("Bairro Teste");
            imovelAlugado.setCep("12345-678");
            imovelAlugado.setMetragem(100);
            imovelAlugado.setDormitorios(3);
            imovelAlugado.setBanheiros(2);
            imovelAlugado.setSuites(1);
            imovelAlugado.setVagasGaragem(2);
            imovelAlugado.setValorAluguelSugerido(new BigDecimal("1000.00"));
            imovelAlugado.setObs("");
            imoveisRepo.salvarOuAlterar(imovelAlugado);

            Imovel imovelVago = new Imovel();
            imovelVago.setLogradouro("Rua 2");
            imovelVago.setBairro("Longíquo");
            imovelVago.setCep("65345-678");
            imovelVago.setMetragem(150);
            imovelVago.setDormitorios(2);
            imovelVago.setBanheiros(2);
            imovelVago.setSuites(1);
            imovelVago.setVagasGaragem(0);
            imovelVago.setValorAluguelSugerido(new BigDecimal("1500.00"));
            imovelVago.setObs("");
            imoveisRepo.salvarOuAlterar(imovelVago);

            em.getTransaction().commit();

            System.out.println("Antes do imóvel 1 ser alugado");
            List<Imovel> imoveisPorPreco = alugueisRepo.listarImoveisDisponiveisPorPreco(new BigDecimal("2000.00"));
            System.out.println("Imóveis disponíveis por limite de preço: ");
            for (Imovel imoveis : imoveisPorPreco) {
                System.out.println(imoveis);
            }

            em.getTransaction().begin();

            Locacao locacao = new Locacao();
            locacao.setImovel(imovelAlugado);
            locacao.setValorAluguel(imovelAlugado.getValorAluguelSugerido());
            locacao.setPercentualMulta(new BigDecimal("5.00"));
            locacao.setDiaVencimento(5);
            locacao.setDataInicio(LocalDate.of(2025, 1, 1));
            locacao.setDataFim(LocalDate.of(2026, 1, 1));
            locacao.setAtivo(true);
            locacaoRepo.salvarOuAlterar(locacao);

            //Ligando locacao ao imóvel
            //imovelAlugado.getIdLocacoes().add(locacao);
            //imoveisRepo.criarOuAtualizar(imovelAlugado);

            // Atualizando o Imóvel Vago
            imovelVago.setObs("É longe de verdade.");
            imoveisRepo.salvarOuAlterar(imovelVago);

            // Commit da segunda operação
            em.getTransaction().commit();

            System.out.println("Após o imóvel 1 ser alugado");
            imoveisPorPreco = alugueisRepo.listarImoveisDisponiveisPorPreco(new BigDecimal("2000.00"));
            System.out.println("Imóveis disponíveis por limite de preço: ");
            for (Imovel imoveis : imoveisPorPreco) {
                System.out.println(imoveis);
            }

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            emf.close();
        }
    }

}
