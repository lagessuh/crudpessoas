package com.teste.crudpessoas.controller;

import com.teste.crudpessoas.model.Pessoa;
import com.teste.crudpessoas.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/")
    public String paginaMenu() {
        return "index";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("pessoas", pessoaService.listarTodas());
        return "pessoas/listar";
    }

@PostMapping("/deletar/{id}")
public String deletar(@PathVariable Long id) {
    pessoaService.deletar(id);
    return "redirect:/pessoas/listar";
}



    @GetMapping("/novo")
    public String novaPessoa(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "pessoas/form-novo";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute Pessoa pessoa, BindingResult result) {
        if (result.hasErrors()) {
            return "pessoas/form-novo";
        }
        pessoaService.salvar(pessoa);
        return "redirect:/pessoas/listar";
    }

//    @GetMapping("/editar/{id}")
//    public String editar(@PathVariable Long id, Model model) {
//        Pessoa pessoa = pessoaService.buscarPorId(id);
//        model.addAttribute("pessoa", pessoa);
//        return "pessoas/form-editar";
//    }
@GetMapping("/editar/{id}")
public String editar(@PathVariable Long id, Model model) {
    Pessoa pessoa = pessoaService.buscarPorId(id);
    // Adicione um System.out para conferir:
    System.out.println("DATA: " + pessoa.getDataNascimento());
    model.addAttribute("pessoa", pessoa);
    return "pessoas/form-editar";
}

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute Pessoa pessoa, BindingResult result) {
        if (result.hasErrors()) {
            return "pessoas/form-editar";
        }
        pessoaService.atualizar(id, pessoa);
        return "redirect:/pessoas/listar";
    }

}