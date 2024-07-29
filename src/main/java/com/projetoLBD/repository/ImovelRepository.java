package com.projetoLBD.repository;

import com.projetoLBD.entity.Imovel;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ImovelRepository extends GenericDAO<Imovel> {

    public ImovelRepository(EntityManager entityManager) {
        super(entityManager);
    }
}