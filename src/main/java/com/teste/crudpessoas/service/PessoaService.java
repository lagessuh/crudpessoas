package com.teste.crudpessoas.service;

import com.teste.crudpessoas.model.Pessoa;
import com.teste.crudpessoas.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public String salvar(Pessoa pessoa) {
        if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        if (pessoa.getDataNascimento().isAfter(LocalDate.now())) {
            throw new RuntimeException("Data de nascimento não pode ser no futuro");
        }

        if (!pessoa.getEmail().matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
            throw new RuntimeException("E-mail inválido");
        }

        if (!isCpfValido(pessoa.getCpf())) {
            throw new RuntimeException("CPF inválido");
        }

        pessoaRepository.save(pessoa);
        return "Pessoa cadastrada com sucesso";
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll();
    }

    public Pessoa atualizar(Long id, Pessoa pessoa) {
        Pessoa existente = buscarPorId(id);
        existente.setNome(pessoa.getNome());
        existente.setCpf(pessoa.getCpf());
        existente.setDataNascimento(pessoa.getDataNascimento());
        existente.setEmail(pessoa.getEmail());
        return pessoaRepository.save(existente);
    }

    public void deletar(Long id) {
        Pessoa pessoa = buscarPorId(id);
        pessoaRepository.delete(pessoa);
    }

    public List<Pessoa> buscarPorNome(String nome) {
        return pessoaRepository.findByNomeContainingIgnoreCase(nome);
    }

    private boolean isCpfValido(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma1 = 0, soma2 = 0;
            for (int i = 0; i < 9; i++) {
                int digito = Character.getNumericValue(cpf.charAt(i));
                soma1 += digito * (10 - i);
                soma2 += digito * (11 - i);
            }

            int digito1 = (soma1 * 10) % 11;
            if (digito1 == 10) digito1 = 0;

            soma2 += digito1 * 2;
            int digito2 = (soma2 * 10) % 11;
            if (digito2 == 10) digito2 = 0;

            return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
                    digito2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }





}
