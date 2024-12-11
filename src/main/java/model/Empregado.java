package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Empregado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    
    @ManyToMany
    @JoinTable(
        name = "empregado_projeto",
        joinColumns = @JoinColumn(name = "empregado_id"),
        inverseJoinColumns = @JoinColumn(name = "projeto_id")
    )
    private Set<Projeto> projetos = new HashSet<>();


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<Projeto> projetos) {
        this.projetos = projetos;
    }
}