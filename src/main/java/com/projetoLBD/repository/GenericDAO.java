package com.projetoLBD.repository;

import com.projetoLBD.entity.EntidadeBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Objects;

public class GenericDAO<T extends EntidadeBase> {

    private final EntityManager entityManager;

    public GenericDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public T buscaPorId(Class<T> clazz, Integer id) {
        return entityManager.find(clazz, id);
    }

    public T salvarOuAlterar(T entidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (Objects.isNull(entidade.getId())) {
                entityManager.persist(entidade);
            } else {
                entidade = entityManager.merge(entidade);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        return entidade;
    }

    public void remover(T entidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(entidade) ? entidade : entityManager.merge(entidade));
            entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<T> listarTodos(Class<T> clazz) {
        return entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
    }
}
