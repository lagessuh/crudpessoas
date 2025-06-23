package com.teste.crudpessoas.service;

import com.teste.crudpessoas.model.Pessoa;
import com.teste.crudpessoas.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa salvar(Pessoa pessoa) {
        if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        return pessoaRepository.save(pessoa);
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




}
