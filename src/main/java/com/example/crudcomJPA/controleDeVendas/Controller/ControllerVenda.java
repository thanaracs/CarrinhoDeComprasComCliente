package com.example.crudcomJPA.controleDeVendas.Controller;


import com.example.crudcomJPA.controleDeVendas.Model.ClientePF;
import com.example.crudcomJPA.controleDeVendas.Model.ItemVenda;
import com.example.crudcomJPA.controleDeVendas.Model.Venda;
import com.example.crudcomJPA.controleDeVendas.Repository.ClientePFRepository;
import com.example.crudcomJPA.controleDeVendas.Repository.ProdutoRepository;
import com.example.crudcomJPA.controleDeVendas.Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Transactional
@Controller
@Scope("request")
@RequestMapping("vendas")
public class ControllerVenda {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    Venda venda;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ClientePFRepository clientepfrepository;

    @ResponseBody

    @GetMapping("/carrinho")
    public ModelAndView form(Venda venda) {
        ModelMap model = new ModelMap();
        model.addAttribute("produto", produtoRepository.produtos());
        model.addAttribute("itemVenda", new ItemVenda());
        model.addAttribute("venda", new Venda());
        model.addAttribute("clientePF", clientepfrepository.clientesPF());
        venda.TotalVenda();
        return new ModelAndView("/vendas/carrinho", model);
    }


    @GetMapping("/list")
    //lista de produtos selecionaros para a compra  /venda
    public ModelAndView listar (Venda venda, ModelMap model){
        model.addAttribute("vendas", vendaRepository.vendas());
        return new ModelAndView("/vendas/list", model);
    }

    @PostMapping("/add")
    public ModelAndView add(ItemVenda itemVenda) {
        itemVenda.setVenda(venda);
        itemVenda.setProduto(produtoRepository.produto(itemVenda.getProduto().getIdProduto()));
        itemVenda.TotalItem();
        venda.setItemVenda(itemVenda);
        venda.TotalVenda();
        return new ModelAndView("redirect:/vendas/carrinho");
    }

       @PostMapping("/addCliente")
    public ModelAndView addCliente(Venda venda, ClientePF cliente) {
        cliente.setVenda(venda);
        venda.setCliente(cliente);
        return new ModelAndView("redirect:/vendas/form");
    }

    @PostMapping("/save")
    public ModelAndView save(Venda venda) {
        this.venda.setId(0);
        this.venda.setLocalDate(venda.getLocalDate());
        this.venda.TotalVenda();
        this.venda.setCliente(venda.getCliente());
        vendaRepository.save(this.venda);
        this.venda.getItemVenda().clear();
        return new ModelAndView("redirect:/vendas/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("venda", vendaRepository.Venda(id));
        return new ModelAndView("/vendas/carrinho", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Venda venda) {
        vendaRepository.update(venda);
        return new ModelAndView("redirect:/vendas/list");
    }

    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") int id){
        for(int i = 0; i < venda.getItemVenda().size(); i++){
            if(venda.getItemVenda().get(i).getProduto().getIdProduto()==(id)){
                venda.getItemVenda().remove(i);
            }
        }
        return new ModelAndView("redirect:/vendas/carrinho");
    }
}