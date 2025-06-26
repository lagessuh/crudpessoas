package com.teste.crudpessoas.controller;

import com.teste.crudpessoas.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PessoaController.class)
public class IndexHtmlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @Test
    void deveConterLinksCadastrarEVisualizarNoMenu() throws Exception {
        mockMvc.perform(get("/pessoas/")) // ou "/" se o mapping estiver no root
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Cadastrar Pessoa")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Visualizar Pessoas")));
    }
}
