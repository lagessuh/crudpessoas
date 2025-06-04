package com.teste.crudpessoas;

import org.springframework.boot.SpringApplication;

public class TestCrudpessoasApplication {

	public static void main(String[] args) {
		SpringApplication.from(CrudpessoasApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
