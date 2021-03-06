package com.example.crudcomJPA.controleDeVendas.Controller;

import com.example.crudcomJPA.controleDeVendas.Model.Produto;
import com.example.crudcomJPA.controleDeVendas.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("produtos")
public class ControllerProdutos {

    @Autowired
    ProdutoRepository produtoRepository;


    @GetMapping("/cadastrar")
    public String form(Produto produto) {
        return "/produtos/cadastrar-produtos";
    }

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("produto", produtoRepository.produtos());
        return new ModelAndView("/produtos/listar-produtos", model);
    }

    @GetMapping("/edit/{idProduto}")
    public ModelAndView edit(@PathVariable("idProduto") int idProduto, ModelMap model) {
        model.addAttribute("produto", produtoRepository.produto(idProduto));
        return new ModelAndView("/produtos/cadastrar-produtos", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Produto produto) {
        produtoRepository.save(produto);
        return new ModelAndView("redirect:/produtos/listar");
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        produtoRepository.update(produto);
        return new ModelAndView("redirect:/produtos/listar");
    }

    @GetMapping("/remove/{idProduto}")
    public ModelAndView remove(@PathVariable("idProduto") int idProduto) {
        produtoRepository.remove(idProduto);
        return new ModelAndView("redirect:/produtos/listar");
    }


}