package com.teste.crudpessoas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class IndexPageTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // Caminho do ChromeDriver, se não estiver no PATH:
        // System.setProperty("webdriver.chrome.driver", "CAMINHO_PARA_SEU_CHROMEDRIVER");

        driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
    }

    @Test
    void testHomePageElementosVisiveis() throws InterruptedException {
        String pageTitle = driver.getTitle();
        assertEquals("CRUD de Pessoas", pageTitle);

        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertTrue(h1.isDisplayed());
        assertEquals("Bem-vindo ao CRUD de Pessoas", h1.getText());

        WebElement btnCadastrar = driver.findElement(By.linkText("Cadastrar Pessoa"));
        assertTrue(btnCadastrar.isDisplayed());

        WebElement btnVisualizar = driver.findElement(By.linkText("Visualizar Pessoas"));
        assertTrue(btnVisualizar.isDisplayed());

        // Espera 3 segundos antes de fechar, apenas para observar
        Thread.sleep(3000);
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
