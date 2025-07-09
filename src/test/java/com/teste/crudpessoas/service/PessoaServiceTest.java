package com.teste.crudpessoas.service;
import com.teste.crudpessoas.model.Pessoa;
import com.teste.crudpessoas.repository.PessoaRepository;
import com.teste.crudpessoas.service.PessoaService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class PessoaServiceIT {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void deveRejeitarCadastroDeCpfDuplicadoNaPratica() {
        Pessoa primeira = new Pessoa(null, "Lucas", "02974947166", LocalDate.of(1990, 1, 1), "lucas@email.com");
        Pessoa duplicada = new Pessoa(null, "João", "02974947166", LocalDate.of(1992, 5, 10), "joao@email.com");

        pessoaService.salvar(primeira); // CPF salvo com sucesso

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            pessoaService.salvar(duplicada); // CPF duplicado
        });

        assertEquals("CPF já cadastrado", ex.getMessage());
    }

    @Test
    void deveRejeitarCadastroComDataNascimentoFutura() {
        Pessoa pessoaFutura = new Pessoa(
                null,
                "Futuro",
                "11122233344",
                LocalDate.now().plusDays(1), // data futura
                "futuro@email.com"
        );

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            pessoaService.salvar(pessoaFutura);
        });

        assertEquals("Data de nascimento não pode ser no futuro", ex.getMessage());
    }

    @Test
    void deveRetornarMensagemDeSucessoAoCadastrarPessoa() {
        Pessoa pessoa = new Pessoa(null, "Ana", "99988877766", LocalDate.of(1990, 1, 1), "ana@email.com");

        String resposta = pessoaService.salvar(pessoa);

        assertEquals("Pessoa cadastrada com sucesso", resposta);
    }

    @Test
    void deveRejeitarCadastroComEmailInvalido() {
        Pessoa pessoa = new Pessoa(null, "Clara", "55544433322", LocalDate.of(1992, 2, 2), "email_invalido");

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            pessoaService.salvar(pessoa);
        });

        assertEquals("E-mail inválido", ex.getMessage());
    }

    @Test
    void deveRejeitarCadastroComCpfInvalido() {
        Pessoa pessoa = new Pessoa(null, "Carlos", "12345678900", LocalDate.of(1990, 1, 1), "carlos@email.com");

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            pessoaService.salvar(pessoa);
        });

        assertEquals("CPF inválido", ex.getMessage());
    }
}
