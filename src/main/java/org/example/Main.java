package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.TipoImovel;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando testes do JPA...");

        // 1. Inicia a Fábrica de Gerenciadores de Entidade
        // O nome "imobiliaria-pu" deve ser EXATAMENTE o mesmo definido no persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("imobiliaria-pu");

        // 2. Cria um Gerenciador de Entidade (Conexão ativa)
        EntityManager em = emf.createEntityManager();

        try {
            // Se chegou até aqui sem erro, a conexão funcionou!
            System.out.println("\n--------------------------------------------------");
            System.out.println("SUCESSO! O Hibernate conectou e validou as tabelas.");
            System.out.println("--------------------------------------------------\n");

            // TESTE EXTRA: Vamos tentar salvar um dado simples para garantir?
            // Vamos criar um Tipo de Imóvel (ex: Apartamento)
            em.getTransaction().begin();

            TipoImovel novoTipo = new TipoImovel();
            novoTipo.setDescricao("Apartamento Duplex");

            em.persist(novoTipo); // Salva no banco

            em.getTransaction().commit(); // Confirma a transação

            System.out.println("Teste de inserção realizado. ID gerado: " + novoTipo.getId());

        } catch (Exception e) {
            System.err.println("ERRO AO CONECTAR OU MANIPULAR O BANCO:");
            e.printStackTrace();
            // Rollback caso tenha dado erro no meio da transação
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            // 3. Fechar conexões
            em.close();
            emf.close();
        }
    }
}