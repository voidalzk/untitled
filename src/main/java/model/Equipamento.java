package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Alocacao> alocacoes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Set<Alocacao> getAlocacoes() {
        return alocacoes;
    }

    public void setAlocacoes(Set<Alocacao> alocacoes) {
        this.alocacoes = alocacoes;
    }
}