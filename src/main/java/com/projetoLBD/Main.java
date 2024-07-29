package com.projetoLBD;

import com.projetoLBD.entity.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Cria uma fábrica de EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("imobiliaria_pu");

        // Cria um EntityManager
        EntityManager em = emf.createEntityManager();

        // Inicia uma transação
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Cria e persiste um novo Cliente
            Cliente cliente = new Cliente();
            cliente.setNome("João Silva");
            cliente.setCpf("123.456.789-00");
            cliente.setTelefone("123456789");
            cliente.setEmail("joao@example.com");
            cliente.setDataNascimento(LocalDate.of(2005, 12, 23));
            em.persist(cliente);


            // Commit da transação
            tx.commit();
        } catch (Exception e) {
            // Rollback em caso de erro
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager
            if (em.isOpen()) {
                em.close();
            }
            // Fecha o EntityManagerFactory
            emf.close();
        }
    }
}
