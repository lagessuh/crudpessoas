package com.teste.crudpessoas.controller;

import com.teste.crudpessoas.model.Pessoa;
import com.teste.crudpessoas.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private PessoaController pessoaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void paginaMenu_deveRetornarIndex() {
        String view = pessoaController.paginaMenu();
        assertEquals("index", view);
    }


    @Test
    void listar_deveAdicionarPessoasAoModeloERetornarView() {
        List<Pessoa> pessoas = List.of(
                new Pessoa(1L, "João", "12345678900", LocalDate.of(1990, 1, 1), "joao@email.com")
        );
        when(pessoaService.listarTodas()).thenReturn(pessoas);

        String view = pessoaController.listar(null, model); // ajuste aqui

        verify(model).addAttribute("pessoas", pessoas);
        verify(model).addAttribute("busca", null); // importante: o método também adiciona "busca"
        assertEquals("pessoas/listar", view);
    }


    @Test
    void novaPessoa_deveRetornarFormularioComPessoaNova() {
        String view = pessoaController.novaPessoa(model);

        verify(model).addAttribute(eq("pessoa"), any(Pessoa.class));
        assertEquals("pessoas/form-novo", view);
    }

    @Test
    void salvar_deveSalvarPessoaQuandoNaoHaErros() {
        Pessoa pessoa = new Pessoa(null, "Maria", "11122233344", LocalDate.of(1995, 5, 5), "maria@email.com");

        when(bindingResult.hasErrors()).thenReturn(false);

        String view = pessoaController.salvar(pessoa, bindingResult);

        verify(pessoaService).salvar(pessoa);
        assertEquals("redirect:/pessoas/listar", view);
    }

    @Test
    void salvar_deveRetornarFormularioQuandoHaErros() {
        Pessoa pessoa = new Pessoa();
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = pessoaController.salvar(pessoa, bindingResult);

        verify(pessoaService, never()).salvar(any());
        assertEquals("pessoas/form-novo", view);
    }

    @Test
    void editar_deveBuscarPessoaEAdicionarAoModelo() {
        Pessoa pessoa = new Pessoa(1L, "Carlos", "98765432100", LocalDate.of(1988, 8, 8), "carlos@email.com");

        when(pessoaService.buscarPorId(1L)).thenReturn(pessoa);

        String view = pessoaController.editar(1L, model);

        verify(model).addAttribute("pessoa", pessoa);
        assertEquals("pessoas/form-editar", view);
    }

    @Test
    void atualizar_deveAtualizarPessoaQuandoNaoHaErros() {
        Pessoa pessoa = new Pessoa(1L, "Lucas", "44455566677", LocalDate.of(1993, 3, 3), "lucas@email.com");
        when(bindingResult.hasErrors()).thenReturn(false);

        String view = pessoaController.atualizar(1L, pessoa, bindingResult);

        verify(pessoaService).atualizar(1L, pessoa);
        assertEquals("redirect:/pessoas/listar", view);
    }

    @Test
    void atualizar_deveRetornarFormularioQuandoHaErros() {
        Pessoa pessoa = new Pessoa();
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = pessoaController.atualizar(1L, pessoa, bindingResult);

        verify(pessoaService, never()).atualizar(anyLong(), any());
        assertEquals("pessoas/form-editar", view);
    }

    @Test
    void deletar_deveChamarServicoEredirecionar() {
        String view = pessoaController.deletar(1L);

        verify(pessoaService).deletar(1L);
        assertEquals("redirect:/pessoas/listar", view);
    }

    @Test
    void buscarPorNome_deveRetornarListaFiltrada() {
        String nomeBuscado = "ana";
        List<Pessoa> resultadoBusca = List.of(
                new Pessoa(1L, "Ana Maria", "12345678900", LocalDate.of(1980, 4, 1), "ana@email.com")
        );

        when(pessoaService.buscarPorNome(nomeBuscado)).thenReturn(resultadoBusca);

        String view = pessoaController.buscarPorNome(nomeBuscado, model);

        verify(pessoaService).buscarPorNome(nomeBuscado);
        verify(model).addAttribute("pessoas", resultadoBusca);
        assertEquals("pessoas/listar", view);
    }
}
