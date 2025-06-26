package com.teste.crudpessoas.controller;

import com.teste.crudpessoas.model.Pessoa;
import com.teste.crudpessoas.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FormEditarHtmlUnitTest {

    @Mock
    private PessoaService pessoaService;

    @Mock
    private Model model;

    @InjectMocks
    private PessoaController pessoaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarFormularioDeEdicaoComPessoaPreenchida() {
        Pessoa pessoa = new Pessoa(1L, "Maria", "12345678900", LocalDate.of(1990, 1, 1), "maria@email.com");
        when(pessoaService.buscarPorId(1L)).thenReturn(pessoa);

        String viewName = pessoaController.editar(1L, model);

        verify(model).addAttribute("pessoa", pessoa);
        assertEquals("pessoas/form-editar", viewName);
    }
}
