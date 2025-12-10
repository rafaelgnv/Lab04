package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.entity.Aluguel;
import org.example.entity.Locacao;
import java.util.List;

public class AluguelDAO {

    private EntityManager em;

    public AluguelDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Aluguel aluguel) {
        this.em.persist(aluguel);
    }

    public void atualizar(Aluguel aluguel) {
        this.em.merge(aluguel);
    }

    // Listar alugueis de uma locação ordenados pela data (mais recente primeiro)
    public List<Aluguel> buscarPorLocacao(Locacao locacao) {
        String jpql = "SELECT a FROM Aluguel a WHERE a.locacao = :locacao ORDER BY a.dataVencimento DESC";
        return em.createQuery(jpql, Aluguel.class)
                .setParameter("locacao", locacao)
                .getResultList();
    }
}