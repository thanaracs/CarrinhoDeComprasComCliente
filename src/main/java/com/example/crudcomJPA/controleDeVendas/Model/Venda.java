package com.example.crudcomJPA.controleDeVendas.Model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Scope ("session")
@Component
@Entity
public class Venda implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVenda;
    private LocalDate localDate = LocalDate.now();

    @OneToMany(mappedBy = "venda", cascade = CascadeType.PERSIST)
    private List<ItemVenda> itemVenda = new ArrayList<>();

    @ManyToOne
    private ClientePF cliente;

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }
    public int getIdVenda() {
        return idVenda;
    }
    public void setId(int idVenda) {
        this.idVenda = idVenda;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<ItemVenda> getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda.add(itemVenda);
    }

    public double TotalVenda() {
        double Total = 0;
        for (ItemVenda i : itemVenda) {
            Total += i.TotalItem();
        }
        return Total;
    }

    public int QtdTotalItem() {
        int QtdTotal = 0;
        for (ItemVenda i : itemVenda) {
            QtdTotal = QtdTotal+i.getQuantidade();
        }
        return QtdTotal;
    }




}
