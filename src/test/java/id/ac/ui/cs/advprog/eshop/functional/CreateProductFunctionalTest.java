package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    @Test
    void testCreateProduct(WebDriver driver) {
        driver.get("http://localhost:8080/product/list");

        driver.findElement(By.linkText("Create Product")).click();

        driver.findElement(By.id("nameInput")).sendKeys("Functional Test Product");
        driver.findElement(By.id("quantityInput")).sendKeys("10");

        driver.findElement(By.tagName("button")).click();

        assertTrue(driver.getPageSource().contains("Functional Test Product"));
    }
}