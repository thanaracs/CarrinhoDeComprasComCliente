package com.example.crudcomJPA.controleDeVendas.Repository;

import com.example.crudcomJPA.controleDeVendas.Model.ClientePF;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Transactional
@Repository
public class ClientePFRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(ClientePF clientePF) {
        em.persist(clientePF);
    }

    public ClientePF clientePF(int idCliente) {
        return em.find(ClientePF.class, idCliente);
    }

    @SuppressWarnings("unchecked")
    public List<ClientePF> clientesPF() {
        Query query = em.createQuery("from ClientePF");
        return query.getResultList();
    }

    public void remove(int idCliente) {
        ClientePF pf = em.find(ClientePF.class, idCliente);
        em.remove(pf);
    }

    public void update(ClientePF clientePF) {
        em.merge(clientePF);
    }
}