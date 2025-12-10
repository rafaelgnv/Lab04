package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.entity.Cliente;
import org.example.entity.Locacao;
import java.util.List;

public class LocacaoDAO {

    private EntityManager em;

    public LocacaoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Locacao locacao) {
        this.em.persist(locacao);
    }

    public void atualizar(Locacao locacao) {
        this.em.merge(locacao);
    }

    // Listar locações ativas (ativo = true)
    public List<Locacao> listarAtivas() {
        String jpql = "SELECT l FROM Locacao l WHERE l.ativo = true";
        return em.createQuery(jpql, Locacao.class).getResultList();
    }

    public List<Locacao> buscarPorInquilino(Cliente inquilino) {
        String jpql = "SELECT l FROM Locacao l WHERE l.inquilino = :inquilino";
        return em.createQuery(jpql, Locacao.class)
                .setParameter("inquilino", inquilino)
                .getResultList();
    }
}