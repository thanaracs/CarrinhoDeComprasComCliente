package com.example.crudcomJPA.controleDeVendas.Repository;


import com.example.crudcomJPA.controleDeVendas.Model.Venda;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class VendaRepository {
    @PersistenceContext
    private EntityManager em;

    public Venda Venda(int idVenda) {
        return em.find(Venda.class, idVenda);
    }

    public void save(Venda venda) {
        em.persist(venda);
    }

    @SuppressWarnings("unchecked")
    public List<Venda> vendas() {
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }

    public void remove(int idVenda){
        Venda v = em.find(Venda.class, idVenda);
        em.remove(v);
    }

    public void update(Venda venda) {
        em.merge(venda);
    }

}