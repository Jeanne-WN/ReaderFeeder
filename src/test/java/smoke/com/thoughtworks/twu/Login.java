package smoke.com.thoughtworks.twu;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login {
    WebDriver driver;

    @Before
    public void setup(){
        driver = new FirefoxDriver();
    }

    @Test
    public void openURL() {
        driver.get("https://mail.thoughtworks.com");
        driver.findElement(By.id("username")).sendKeys("lavanyam");
        driver.findElement(By.id("password")).sendKeys("lavanya");
        driver.findElement(By.className("btn-submit")).click();
        Assert.assertEquals(driver.getTitle(),"CAS \u2013 Central Authentication " +
                "Service");

    }
}