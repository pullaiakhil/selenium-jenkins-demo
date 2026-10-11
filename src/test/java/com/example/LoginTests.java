package com.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests {
    private static WebDriver driver;

    @BeforeAll
    static void setUp() throws Exception {
        LoginPage.start();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) driver.quit();
        LoginPage.stop();
    }

    @Test
    void validCredentialsShowSuccessMessage() {
        driver.get(LoginPage.url());
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Pass123!");
        driver.findElement(By.id("loginButton")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> !d.findElement(By.id("message")).getText().isBlank());
        assertEquals("Login successful", driver.findElement(By.id("message")).getText());
    }

    @Test
    void invalidCredentialsShowErrorMessage() {
        driver.get(LoginPage.url());
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("wrong-password");
        driver.findElement(By.id("loginButton")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> !d.findElement(By.id("message")).getText().isBlank());
        assertEquals("Invalid username or password",
                driver.findElement(By.id("message")).getText());
    }

    @Test
    void loginPageContainsRequiredFields() {
        driver.get(LoginPage.url());
        assertTrue(driver.findElement(By.id("username")).isDisplayed());
        assertTrue(driver.findElement(By.id("password")).isDisplayed());
        assertTrue(driver.findElement(By.id("loginButton")).isDisplayed());
    }
}
