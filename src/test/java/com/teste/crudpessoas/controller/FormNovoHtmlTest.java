package com.teste.crudpessoas.controller;

import com.teste.crudpessoas.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PessoaController.class)
public class FormNovoHtmlTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PessoaService pessoaService;

    @Test
    void formularioDeCadastroDeveConterCamposEOpcoes() throws Exception {
        mockMvc.perform(get("/pessoas/novo"))
                .andExpect(status().isOk())
                // Título da página
                .andExpect(content().string(containsString("Cadastrar Pessoa")))
                // Campos do formulário
                .andExpect(content().string(containsString("id=\"nome\"")))
                .andExpect(content().string(containsString("id=\"cpf\"")))
                .andExpect(content().string(containsString("id=\"dataNascimento\"")))
                .andExpect(content().string(containsString("id=\"email\"")))
                // Botão de envio
                .andExpect(content().string(containsString("type=\"submit\"")))
                .andExpect(content().string(containsString("Salvar")))
                // Link de retorno
                .andExpect(content().string(containsString("Voltar ao menu")));
    }
}
