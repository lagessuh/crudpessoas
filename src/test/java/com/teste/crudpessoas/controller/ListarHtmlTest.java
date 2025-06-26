package com.teste.crudpessoas.controller;

import com.teste.crudpessoas.model.Pessoa;
import com.teste.crudpessoas.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PessoaController.class)
public class ListarHtmlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @Test
    void verificarElementosHtmlDaPaginaDePessoas() throws Exception {
        Pessoa pessoa = new Pessoa(1L, "Ana", "12345678900", LocalDate.of(2000, 1, 1), "ana@email.com");
        when(pessoaService.listarTodas()).thenReturn(List.of(pessoa));

        mockMvc.perform(get("/pessoas/listar"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ana")))
                .andExpect(content().string(containsString("12345678900")))
                .andExpect(content().string(containsString("Editar")))
                .andExpect(content().string(containsString("Excluir")));
    }
}
