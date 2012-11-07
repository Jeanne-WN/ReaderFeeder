package functional.com.thoughtworks.twu;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ViewBookFunctionalTest{
    private WebDriver webDriver;
    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
    }
    @Ignore
    public void shouldValidateAllElementsPresent(){
        login();
        webDriver.get("127.0.0.1:8080/twu/viewbook?booktitle=The Casual Vacancy");
        assertOnBookTitle();
        assertOnBookCover();
        assertOnBookDescription();
        assertOnIsbn();
        assertOnAuthor();
    }
    private void assertOnIsbn() {

        assertEquals(true, webDriver.findElement(By.className("isbn")).isDisplayed());
    }

    private void assertOnAuthor() {
        assertEquals(true, webDriver.findElement(By.className("author"))
                .isDisplayed());
    }
    private void assertOnBookTitle() {
        WebElement titleElement = locateElement("h1.title");
        assertEquals("The Casual Vacancy", titleElement.getText());
    }

    private void assertOnBookCover() {
        WebElement imageElement= locateElement("img.book-img");
        assertThat(imageElement.getAttribute("src").isEmpty(), is(false));
    }

    private void assertOnBookDescription() {
        WebElement descriptionElement = locateElement("section.description");
        assertThat(descriptionElement.getText().isEmpty(), is(false));
    }
    private WebElement locateElement(String selector){
        return webDriver.findElement(By.cssSelector(selector));
    }

    private void login() {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
    }

    @Ignore
    public void shouldDisplayBookNotFoundWhenBookIsNotPresent(){
        login();

        webDriver.get("127.0.0.1:8080/twu/viewbook?booktitle=alkdhaksdh");
        assertEquals("Could not find book",webDriver.findElement(By.className("title")).getText());
    }

    @Ignore
    public void shouldShowTheSameBook() {
        login();
        webDriver.get("127.0.0.1:8080/twu/viewbook?booktitle=The Casual Vacancy");
        String title1= webDriver.findElement(By.className("title")).getText();
        webDriver.get("127.0.0.1:8080/twu/viewbook?booktitle=The Casual Vacancy");
        String title2 =webDriver.findElement(By.className("title")).getText();
        assertEquals(title1,title2);
    }
    @Ignore
    public void shouldShowBookEvenIfItHasSameName(){
        login();
        webDriver.get("127.0.0.1:8080/twu/viewbook?booktitle=The Book Of The Dead");
        String title= webDriver.findElement(By.className("title")).getText();
        assertEquals("The Book Of The Dead",title);
    }
}