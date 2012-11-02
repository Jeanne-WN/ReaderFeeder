package main.functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static junit.framework.Assert.assertEquals;

public class CreateUserFunctionalTest {
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        performSuccessfulLogin();
    }

    @Test
    public void shouldRedirectToCreateUserPage() {
        assertEquals(webDriver.getTitle(), "ReaderFeeder Create Profile");
    }

    @After
    public void tearDown() {
        webDriver.close();
    }

    private void performSuccessfulLogin() {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
    }
}
