package com.teste.crudpessoas.service;

import com.teste.crudpessoas.model.Pessoa;
import com.teste.crudpessoas.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaServiceTest {

    @Mock
    PessoaRepository pessoaRepository;

    @InjectMocks
    PessoaService pessoaService;

    public PessoaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarPessoaComSucesso() {
        Pessoa pessoa = new Pessoa(null, "João Silva", "12345678900", LocalDate.of(1990, 1, 1), "joao@email.com");

        when(pessoaRepository.save(pessoa)).thenReturn(new Pessoa(1L, "João Silva", "12345678900", LocalDate.of(1990, 1, 1), "joao@email.com"));

        Pessoa salva = pessoaService.salvar(pessoa);

        assertNotNull(salva.getId());
        assertEquals("João Silva", salva.getNome());
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    void deveBuscarPessoaPorId() {
        Pessoa pessoa = new Pessoa(1L, "Maria Souza", "98765432100", LocalDate.of(1985, 5, 10), "maria@email.com");

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        Pessoa encontrada = pessoaService.buscarPorId(1L);

        assertNotNull(encontrada);
        assertEquals("Maria Souza", encontrada.getNome());
        verify(pessoaRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoPessoaNaoForEncontrada() {
        when(pessoaRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pessoaService.buscarPorId(999L);
        });

        assertEquals("Pessoa não encontrada", exception.getMessage());
        verify(pessoaRepository).findById(999L);
    }

    @Test
    void deveListarTodasAsPessoas() {
        List<Pessoa> pessoas = List.of(
                new Pessoa(1L, "Ana", "11122233344", LocalDate.of(1992, 2, 2), "ana@email.com"),
                new Pessoa(2L, "Carlos", "55566677788", LocalDate.of(1980, 8, 20), "carlos@email.com")
        );

        when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<Pessoa> resultado = pessoaService.listarTodas();

        assertEquals(2, resultado.size());
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    void deveAtualizarPessoa() {
        Pessoa existente = new Pessoa(1L, "Lucas", "99988877766", LocalDate.of(1995, 3, 15), "lucas@email.com");

        Pessoa atualizada = new Pessoa(1L, "Lucas Silva", "99988877766", LocalDate.of(1995, 3, 15), "lucassilva@email.com");

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(pessoaRepository.save(atualizada)).thenReturn(atualizada);

        Pessoa resultado = pessoaService.atualizar(1L, atualizada);

        assertEquals("Lucas Silva", resultado.getNome());
        assertEquals("lucassilva@email.com", resultado.getEmail());
        verify(pessoaRepository, times(1)).save(atualizada);
    }

    @Test
    void deveDeletarPessoa() {
        Pessoa pessoa = new Pessoa(1L, "Carlos", "55566677788", LocalDate.of(1980, 8, 20), "carlos@email.com");

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        doNothing().when(pessoaRepository).delete(pessoa);

        pessoaService.deletar(1L);

        verify(pessoaRepository, times(1)).delete(pessoa);
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaExistir() {
        Pessoa pessoa = new Pessoa(null, "Lucas", "12345678900", LocalDate.of(1995, 1, 1), "lucas@email.com");

        when(pessoaRepository.existsByCpf("12345678900")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pessoaService.salvar(pessoa);
        });

        assertEquals("CPF já cadastrado", exception.getMessage());
        verify(pessoaRepository, times(1)).existsByCpf("12345678900");
        verify(pessoaRepository, never()).save(any());
    }

    @Test
    void deveBuscarPessoasPorNome() {
        List<Pessoa> pessoas = List.of(
                new Pessoa(1L, "Lucas", "12345678900", LocalDate.of(1990, 1, 1), "lucas@email.com"),
                new Pessoa(2L, "Luciana", "98765432100", LocalDate.of(1992, 2, 2), "luciana@email.com")
        );

        when(pessoaRepository.findByNomeContainingIgnoreCase("luc")).thenReturn(pessoas);

        List<Pessoa> resultado = pessoaService.buscarPorNome("luc");

        assertEquals(2, resultado.size());
        verify(pessoaRepository, times(1)).findByNomeContainingIgnoreCase("luc");
    }



}
