package utfpr.crud.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Carro implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="opcional_fk", nullable=false)
    private Opcional opcional;

    @Column
    private String cor;

    @Column
    private String modelo;

    @ManyToOne
    @JoinColumn(name="marca_fk", nullable=false)
    private Marca marca;

    @Column
    private int ano;

    public Carro(Opcional opcional, Marca marca, String cor, String modelo, int ano) {
        this.opcional = opcional;
        this.marca = marca;
        this.cor = cor;
        this.modelo = modelo;
        this.ano = ano;
    }

    public Carro() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Opcional getOpcional() {
        return opcional;
    }

    public void setOpcional(Opcional opcional) {
        this.opcional = opcional;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isPersisted() {
        return id != null;
    }
}
