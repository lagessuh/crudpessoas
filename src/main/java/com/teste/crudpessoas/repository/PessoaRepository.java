package com.teste.crudpessoas.repository;

import com.teste.crudpessoas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByCpf(String cpf);

    List<Pessoa> findByNomeContainingIgnoreCase(String nome);

}
