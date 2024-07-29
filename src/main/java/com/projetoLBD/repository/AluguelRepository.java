package com.projetoLBD.repository;

import com.projetoLBD.entity.Aluguel;
import com.projetoLBD.entity.Cliente;
import com.projetoLBD.entity.Imovel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class AluguelRepository extends GenericDAO<Aluguel> {

    public AluguelRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Aluguel> listarAlugueisPorInquilino(Cliente inquilino) {
        String jpql = "SELECT a FROM Aluguel a JOIN a.locacao l WHERE l.inquilino = :inquilino";
        TypedQuery<Aluguel> query = getEntityManager().createQuery(jpql, Aluguel.class);
        query.setParameter("inquilino", inquilino);
        return query.getResultList();
    }


    public List<Aluguel> listarAlugueisPorNomeCliente(String nomeCliente) {
        String jpql = "SELECT a FROM Aluguel a JOIN a.locacao l JOIN l.inquilino c WHERE c.nome = :nomeCliente";
        TypedQuery<Aluguel> query = getEntityManager().createQuery(jpql, Aluguel.class);
        query.setParameter("nomeCliente", nomeCliente);
        return query.getResultList();
    }


    public List<Imovel> listarImoveisDisponiveisPorPreco(BigDecimal limitePreco) {
        String jpql = "SELECT i FROM Imovel i WHERE i.valorAluguelSugerido <= :limitePreco AND i.id NOT IN (" +
                "SELECT l.imovel.id FROM Locacao l WHERE l.dataFim IS NULL OR l.dataFim >= CURRENT_DATE)";
        TypedQuery<Imovel> query = getEntityManager().createQuery(jpql, Imovel.class);
        query.setParameter("limitePreco", limitePreco);
        return query.getResultList();
    }


    public List<Aluguel> listarAlugueisComAtraso() {
        String jpql = "SELECT a FROM Aluguel a WHERE a.dataPagamento IS NOT NULL AND a.dataPagamento > a.dataVencimento";
        TypedQuery<Aluguel> query = getEntityManager().createQuery(jpql, Aluguel.class);
        return query.getResultList();
    }
}
