package com.projetoLBD.repository;

import com.projetoLBD.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ClienteRepository extends GenericDAO<Cliente> {

    public ClienteRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public boolean cpfUnico(String cpf) {
        String hql = "SELECT COUNT(c) FROM Cliente c WHERE c.cpf = :cpf";
        Query query = getEntityManager().createQuery(hql);
        query.setParameter("cpf", cpf);
        long count = (long) query.getSingleResult();
        return count == 0;
    }

    public List<Cliente> listarTodos() {
        return getEntityManager().createQuery("FROM Cliente", Cliente.class).getResultList();
    }
}