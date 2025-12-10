package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.entity.Cliente;
import org.example.entity.Imovel;
import java.math.BigDecimal;
import java.util.List;

public class ImovelDAO {

    private EntityManager em;

    public ImovelDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Imovel imovel) {
        this.em.persist(imovel);
    }

    public void atualizar(Imovel imovel) {
        this.em.merge(imovel);
    }

    public List<Imovel> listarTodos() {
        return em.createQuery("SELECT i FROM Imovel i", Imovel.class).getResultList();
    }

    public List<Imovel> buscarPorCep(String cep) {
        return em.createQuery("SELECT i FROM Imovel i WHERE i.cep = :cep", Imovel.class)
                .setParameter("cep", cep)
                .getResultList();
    }

    // Busca por faixa de preço (entre min e max)
    public List<Imovel> buscarPorFaixaPreco(BigDecimal min, BigDecimal max) {
        String jpql = "SELECT i FROM Imovel i WHERE i.valorAluguelSugerido BETWEEN :min AND :max";
        return em.createQuery(jpql, Imovel.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }

    // Busca pela descrição do Tipo (Ex: "Apartamento")
    // Repare que navegamos: Imovel -> TipoImovel -> Descricao
    public List<Imovel> buscarPorTipo(String descricaoTipo) {
        String jpql = "SELECT i FROM Imovel i WHERE i.tipoImovel.descricao LIKE :descricao";
        return em.createQuery(jpql, Imovel.class)
                .setParameter("descricao", "%" + descricaoTipo + "%") // O % permite busca parcial
                .getResultList();
    }

    public List<Imovel> buscarPorProprietario(Cliente proprietario) {
        String jpql = "SELECT i FROM Imovel i WHERE i.proprietario = :proprietario";
        return em.createQuery(jpql, Imovel.class)
                .setParameter("proprietario", proprietario)
                .getResultList();
    }
}