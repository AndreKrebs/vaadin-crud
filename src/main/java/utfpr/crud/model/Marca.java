package utfpr.crud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Marca implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @OneToMany(mappedBy="marca")
    private Set<Carro> carros;

    public Marca() {
        super();
    }

    public Marca(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Carro> getCarros() {
        return carros;
    }

    public void setCarros(Set<Carro> carros) {
        this.carros = carros;
    }

    @Override
    public String toString() {
        return nome;
    }
}
