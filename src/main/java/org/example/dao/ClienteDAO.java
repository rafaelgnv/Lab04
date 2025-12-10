package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.entity.Cliente;
import java.util.List;

public class ClienteDAO {

    private EntityManager em;

    // Injeção de dependência: O DAO precisa de uma conexão aberta para funcionar
    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public void atualizar(Cliente cliente) {
        this.em.merge(cliente);
    }

    public Cliente buscarPorCpf(String cpf) {
        String jpql = "SELECT c FROM Cliente c WHERE c.cpf = :cpf";
        TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
        query.setParameter("cpf", cpf);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorna nulo se não achar
        }
    }

    public Cliente buscarPorEmail(String email) {
        String jpql = "SELECT c FROM Cliente c WHERE c.email = :email";
        TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}