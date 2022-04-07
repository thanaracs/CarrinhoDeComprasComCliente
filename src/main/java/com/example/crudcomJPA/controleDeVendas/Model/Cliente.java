package com.example.crudcomJPA.controleDeVendas.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //junção entre as chaves  estrangeiras
public abstract class Cliente implements Serializable {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int idCliente;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> venda = new ArrayList<>();

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<Venda> getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda.add(venda);
    }

}