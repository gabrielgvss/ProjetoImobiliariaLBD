package com.projetoLBD.repository;

import com.projetoLBD.entity.Imovel;
import com.projetoLBD.entity.ServicoImovel;
import com.projetoLBD.entity.Locacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ServicoImovelRepository extends GenericDAO<ServicoImovel> {

    public ServicoImovelRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public List<ServicoImovel> buscarServicosPorLocacao(Locacao locacao) {
        // Obtendo o imóvel associado à locação
        Imovel imovel = locacao.getImovel();

        // Verificando se o imóvel não é nulo
        if (imovel == null) {
            throw new IllegalArgumentException("A locação não está associada a um imóvel.");
        }

        // Consulta JPQL para buscar serviços associados ao imóvel
        String jpql = "SELECT s FROM ServicoImovel s WHERE s.imovel = :imovel";
        TypedQuery<ServicoImovel> query = getEntityManager().createQuery(jpql, ServicoImovel.class);
        query.setParameter("imovel", imovel);

        return query.getResultList();
    }
}
