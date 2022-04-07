package com.example.crudcomJPA.controleDeVendas.Controller;

import com.example.crudcomJPA.controleDeVendas.Model.ClientePF;
import com.example.crudcomJPA.controleDeVendas.Repository.ClientePFRepository;
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
@RequestMapping("clientes")
public class ControllerClientePF {

    @Autowired
    ClientePFRepository repository;

    @GetMapping("/cadastrar-cliente")
    public String form(ClientePF clientePF) {
        return "/clientes/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("clientePF", repository.clientesPF());
        return new ModelAndView("/clientes/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(ClientePF clientePF) {
        repository.save(clientePF);
        return new ModelAndView("redirect:/clientes/list");
    }

    @GetMapping("/remove/{idCliente}")
    public ModelAndView remove(@PathVariable("idCliente") int idCliente) {
        repository.remove(idCliente);
        return new ModelAndView("redirect:/clientes/list");
    }

    @GetMapping("/edit/{idCliente}")
    public ModelAndView edit(@PathVariable("idCliente") int idCliente, ModelMap model) {
        model.addAttribute("clientePF", repository.clientePF(idCliente));
        return new ModelAndView("/clientes/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(ClientePF clientePF) {
        repository.update(clientePF);
        return new ModelAndView("redirect:/clientes/list");
    }
}
