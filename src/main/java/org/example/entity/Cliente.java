package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_cliente", nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    private String telefone1;
    private String telefone2;
    private String email;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

    // Lista de Imóveis que este cliente possui (Proprietário)
    @OneToMany(mappedBy = "proprietario")
    private List<Imovel> imoveisProprios;

    // Lista de Locações onde este cliente aluga (Inquilino)
    @OneToMany(mappedBy = "inquilino")
    private List<Locacao> locacoes;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Imovel> getImoveisProprios() {
        return imoveisProprios;
    }

    public void setImoveisProprios(List<Imovel> imoveisProprios) {
        this.imoveisProprios = imoveisProprios;
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}