package org.example.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_imovel")
public class TipoImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao", length = 100)
    private String descricao;

    // Relacionamento Um-para-Muitos: Um Tipo tem vários Imóveis
    @OneToMany(mappedBy = "tipoImovel")
    private List<Imovel> imoveis;

    public TipoImovel() {
    }

    public TipoImovel(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Imovel> getImoveis() {
        return imoveis;
    }

    public void setImoveis(List<Imovel> imoveis) {
        this.imoveis = imoveis;
    }

    @Override
    public String toString() {
        return "TipoImovel{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}