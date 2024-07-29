package com.projetoLBD.repository;

import com.projetoLBD.entity.Cliente;
import com.projetoLBD.entity.Imovel;
import com.projetoLBD.entity.Locacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;

public class LocacaoRepository extends GenericDAO<Locacao> {

    public LocacaoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public boolean isImovelDisponivel(Imovel imovel) {
        String hql = "SELECT COUNT(l) FROM Locacao l WHERE l.imovel = :imovel AND l.ativo = true";
        Query query = getEntityManager().createQuery(hql);
        query.setParameter("imovel", imovel);
        long count = (long) query.getSingleResult();
        return count == 0;
    }


    public Locacao salvarOuAlterar(Locacao locacao) {
        if (!isImovelDisponivel(locacao.getImovel())) {
            throw new IllegalStateException("O imóvel não está disponível para locação.");
        }

        EntityTransaction transaction = getEntityManager().getTransaction();
        try {
            transaction.begin();
            if (locacao.getId() == null) {
                getEntityManager().persist(locacao);
            } else {
                locacao = getEntityManager().merge(locacao);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return locacao;
    }

    public List<Locacao> listarPorCliente(Cliente cliente) {
        String hql = "FROM Locacao l WHERE l.inquilino = :cliente";
        Query query = getEntityManager().createQuery(hql, Locacao.class);
        query.setParameter("cliente", cliente);
        return query.getResultList();
    }

    public List<Locacao> listarLocacoesAtivas() {
        String hql = "FROM Locacao l WHERE l.ativo = true";
        Query query = getEntityManager().createQuery(hql, Locacao.class);
        return query.getResultList();
    }
}