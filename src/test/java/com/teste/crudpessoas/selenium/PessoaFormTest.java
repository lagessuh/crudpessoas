//package com.teste.crudpessoas.selenium;
//
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class PessoaFormTest {
//
//    private WebDriver driver;
//
//    @BeforeEach
//    public void setUp() {
//        // Ajuste o caminho para onde está seu chromedriver.exe
//        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
//        driver = new ChromeDriver();
//    }
//
//    @Test
//    public void testCadastroPessoa() {
//        driver.get("http://localhost:8080/pessoas/novo");
//
//        driver.findElement(By.id("nome")).sendKeys("Suellen Lages");
//        driver.findElement(By.id("cpf")).sendKeys("56970552187");
//        driver.findElement(By.id("dataNascimento")).sendKeys("1990-10-11");
//        driver.findElement(By.id("email")).sendKeys("suellen@example.com");
//
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//
//        // Aguarda até que o alerta de sucesso apareça (espera até 5 segundos)
//        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
//        WebElement alerta = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.className("alert-success"))
//        );
//
//        String texto = alerta.getText();
//        Assertions.assertTrue(texto.contains("Pessoa cadastrada com sucesso"), "Mensagem de sucesso não encontrada!");
//    }
//
//    @AfterEach
//    public void tearDown() {
//        // Fecha o navegador
//        driver.quit();
//    }
//}


//package com.teste.crudpessoas.selenium;
//
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.*;
//
//import java.time.Duration;
//import java.util.List;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class PessoaFormTest {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private final String baseUrl = "http://localhost:8080";
//
//    @BeforeEach
//    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    @Test
//    @Order(1)
//    public void fluxoCompleto() {
//        // 1. Acessa o menu inicial
//        driver.get(baseUrl + "/");
//
//        // 2. Clica em "Cadastrar Pessoa"
//        driver.findElement(By.linkText("Cadastrar Pessoa")).click();
//
//        // 3. Preenche o formulário
//        driver.findElement(By.id("nome")).sendKeys("Suellen Selenium");
//        driver.findElement(By.id("cpf")).sendKeys("98765432100");
//        driver.findElement(By.id("dataNascimento")).sendKeys("01-01-1998");
//        driver.findElement(By.id("email")).sendKeys("selenium@example.com");
//
//        // 4. Clica em salvar
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//
//        // 5. Verifica se a mensagem de sucesso apareceu
//        WebElement alerta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
//        Assertions.assertTrue(alerta.getText().contains("Pessoa cadastrada com sucesso"));
//
//        // 6. Busca pela pessoa
//        WebElement inputBusca = driver.findElement(By.name("busca"));
//        inputBusca.clear();
//        inputBusca.sendKeys("Suellen Selenium");
//        driver.findElement(By.cssSelector("button.btn-primary")).click();
//
//        // 7. Clica em "Editar"
//        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Editar"))).click();
//
//        // 8. Altera o nome
//        WebElement campoNome = driver.findElement(By.id("nome"));
//        campoNome.clear();
//        campoNome.sendKeys("Suellen Editada");
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//
//        // 9. Verifica se o nome atualizado aparece na tabela
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
//        List<WebElement> linhas = driver.findElements(By.tagName("tr"));
//        boolean nomeAtualizado = linhas.stream().anyMatch(
//                linha -> linha.getText().contains("Suellen Editada")
//        );
//        Assertions.assertTrue(nomeAtualizado, "Nome não foi atualizado!");
//
//        // 10. Clica em Excluir e confirma
//        WebElement btnExcluir = driver.findElement(By.xpath("//tr[contains(., 'Suellen Editada')]//button[contains(., 'Excluir')]"));
//        String modalId = btnExcluir.getAttribute("data-bs-target").replace("#", ""); // Ex: "modalExcluir12"
//        btnExcluir.click();
//
//// Aguarda o modal ficar visível e com a classe 'show'
//        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(modalId)));
//        wait.until(ExpectedConditions.attributeContains(modal, "class", "show"));
//
//// Clica no botão "Excluir" dentro do modal
//        modal.findElement(By.cssSelector("form button[type='submit']")).click();
//
////        // 10. Clica em Excluir e confirma
////        WebElement btnExcluir = driver.findElement(By.xpath("//tr[contains(., 'Suellen Editada')]//button[contains(., 'Excluir')]"));
////        btnExcluir.click();
////
////        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal")));
////        WebElement modal = driver.findElement(By.className("modal"));
////        modal.findElement(By.cssSelector("form button[type='submit']")).click();
//
//        // 11. Verifica se a pessoa foi removida
//        driver.navigate().refresh();
//        boolean aindaExiste = driver.getPageSource().contains("Suellen Editada");
//        Assertions.assertFalse(aindaExiste, "Pessoa não foi excluída!");
//    }
//
//    @AfterEach
//    public void tearDown() {
//        //driver.quit();
//    }
//}


package com.teste.crudpessoas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PessoaFormTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "http://localhost:8080";

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @Order(1)
    public void fluxoCompleto() {
        // 1. Acessa o menu inicial
        driver.get(baseUrl + "/");
        pause(1000);

        // 2. Clica em "Cadastrar Pessoa"
        driver.findElement(By.linkText("Cadastrar Pessoa")).click();
        pause(1000);

        // 3. Preenche o formulário
        driver.findElement(By.id("nome")).sendKeys("Suellen Selenium");
        pause(500);
        driver.findElement(By.id("cpf")).sendKeys("98765432100");
        pause(500);
        driver.findElement(By.id("dataNascimento")).sendKeys("01-01-1998");
        pause(500);
        driver.findElement(By.id("email")).sendKeys("selenium@example.com");
        pause(500);

        // 4. Clica em salvar
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        pause(1000);

        // 5. Verifica se a mensagem de sucesso apareceu
        WebElement alerta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        Assertions.assertTrue(alerta.getText().contains("Pessoa cadastrada com sucesso"));
        pause(1000);

        // 6. Busca pela pessoa
        WebElement inputBusca = driver.findElement(By.name("busca"));
        inputBusca.clear();
        inputBusca.sendKeys("Suellen Selenium");
        pause(500);
        driver.findElement(By.cssSelector("button.btn-primary")).click();
        pause(1000);

        // 7. Clica em "Editar"
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Editar"))).click();
        pause(1000);

        // 8. Altera o nome
        WebElement campoNome = driver.findElement(By.id("nome"));
        campoNome.clear();
        campoNome.sendKeys("Suellen Editada");
        pause(500);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        pause(1000);

        // 9. Verifica se o nome atualizado aparece na tabela
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        List<WebElement> linhas = driver.findElements(By.tagName("tr"));
        boolean nomeAtualizado = linhas.stream().anyMatch(
                linha -> linha.getText().contains("Suellen Editada")
        );
        Assertions.assertTrue(nomeAtualizado, "Nome não foi atualizado!");
        pause(1000);

        // 10. Clica em Excluir e confirma
        WebElement btnExcluir = driver.findElement(By.xpath("//tr[contains(., 'Suellen Editada')]//button[contains(., 'Excluir')]"));
        String modalId = btnExcluir.getAttribute("data-bs-target").replace("#", "");
        btnExcluir.click();
        pause(1000);

        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(modalId)));
        wait.until(ExpectedConditions.attributeContains(modal, "class", "show"));
        pause(1000);

        modal.findElement(By.cssSelector("form button[type='submit']")).click();
        pause(1000);

        // 11. Verifica se a pessoa foi removida
        driver.navigate().refresh();
        pause(1000);
        boolean aindaExiste = driver.getPageSource().contains("Suellen Editada");
        Assertions.assertFalse(aindaExiste, "Pessoa não foi excluída!");
    }

    @AfterEach
    public void tearDown() {
        // driver.quit();
    }
}

