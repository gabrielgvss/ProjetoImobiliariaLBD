package com.projetoLBD.repository;

import com.projetoLBD.entity.Imovel;
import com.projetoLBD.entity.Profissional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProfissionalRepository extends GenericDAO<Profissional> {

    public ProfissionalRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Imovel> listarImoveisPorProfissional(Profissional profissional) {
        String jpql = "SELECT i FROM Imovel i JOIN ServicoImovel s ON s.imovel = i WHERE s.profissional = :profissional";
        TypedQuery<Imovel> query = getEntityManager().createQuery(jpql, Imovel.class);
        query.setParameter("profissional", profissional);
        return query.getResultList();
    }
}


