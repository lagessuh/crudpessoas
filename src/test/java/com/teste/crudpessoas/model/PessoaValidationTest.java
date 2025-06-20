package com.teste.crudpessoas.model;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PessoaValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveAceitarEmailValido() {
        Pessoa pessoa = new Pessoa(null, "João", "12345678900", LocalDate.of(1990, 1, 1), "joao@email.com");

        Set<ConstraintViolation<Pessoa>> violations = validator.validate(pessoa);

        assertTrue(violations.isEmpty());
    }

    @Test
    void deveRejeitarEmailInvalido() {
        Pessoa pessoa = new Pessoa(null, "Maria", "98765432100", LocalDate.of(1985, 5, 10), "email-invalido");

        Set<ConstraintViolation<Pessoa>> violations = validator.validate(pessoa);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }
}
