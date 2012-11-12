package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.junit.Assert.*;

public class SearchBookFunctionalTest {
    WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        CommonSteps.login(webDriver,"test.twu","Th0ughtW0rks@12");
    }

    @Test
    public void shouldNotDisplayByWhenBookDoesntHaveAuthor()
    {
        CommonSteps.searchBook(webDriver,"Boys' Life","searchByTitle");
        assertThat(webDriver.findElement(By.className("book-author")).getText(), is(""));
    }

    @Test
    public void shouldDisplayBookAuthorAndTitleWhenGoButtonIsClickedAndWeSearchByISBN() {
        CommonSteps.searchBook(webDriver,"9780316228534","searchByISBN");
        assertThat(webDriver.findElement(By.className("book-picture")).isDisplayed(), is(true));
        assertThat(webDriver.findElement(By.className("book-title")).getText(),is("The Casual Vacancy"));
        assertThat(webDriver.findElement(By.className("book-author")).getText(),is("by J. K. Rowling"));
    }

    @Test
    public void
    shouldDisplayBookAuthorAndTitleWhenGoButtonIsClickedAndWeSearchByTitle() {
        CommonSteps.searchBook(webDriver,"mop","searchByTitle");
        assertThat(webDriver.findElement(By.className("book-picture")).isDisplayed(), is(true));
        assertThat(webDriver.findElement(By.className("book-title")).getText(),is("Monster mop"));
        assertThat(webDriver.findElement(By.className("book-author")).getText(),is("by Anthony Laurence"));
    }

    @Test
    public void
    shouldDisplayBookAuthorAndTitleWhenGoButtonIsClickedAndWeSearchByAuthor() {
        CommonSteps.searchBook(webDriver,"Someone","searchByAuthor");
        assertThat(webDriver.findElement(By.className("book-picture")).isDisplayed(), is(true));
        assertThat(webDriver.findElement(By.className("book-title")).getText(),is("The Royal Yacht Britannia"));
        assertThat(webDriver.findElement(By.className("book-author")).getText(),is("by Someone Publishing Limited"));
    }

    @Test
    public void shouldDisplayErrorMessageWhenBookIsNotFound() {
        CommonSteps.searchBook(webDriver,"fasdfafasd","searchByTitle");
        assertThat(webDriver.findElement(By.id("error")).getText(), is("No books were found matching your search " +"criteria. Please try again with a new search criteria."));
    }

    @Test
    public void shouldRememberThePreviousSearchType() {
        CommonSteps.searchBook(webDriver,"fasdfafasd","searchByISBN");
        String actual =  webDriver.findElement(By.name("searchValue")).getAttribute("value");
        final WebElement searchByISBN = webDriver.findElement(By.id("searchByISBN"));
        assertTrue(searchByISBN.isSelected());
        assertThat(actual, is("fasdfafasd"));
    }

    @Test
    public void shouldStayOnSamePageOnRefresh() throws Exception {
        webDriver.get("http://127.0.0.1:8080/twu/search_book");
        webDriver.navigate().refresh();
        assertEquals("Search Results", webDriver.getTitle());

    }

    @Test
    public void shouldDisplayAMaximumOf20Results() throws Exception {
        CommonSteps.searchBook(webDriver,"Harry Potter","searchByTitle");
        webDriver.get("http://127.0.0.1:8080/twu/search_book");
        List<WebElement> list = webDriver.findElements(By.className("book"));
        assertThat(list.size(), is(lessThanOrEqualTo(20)));
    }

    @Test
    public void shouldDisplayErrorMessageWhenNoValueInputted() throws Exception {
        CommonSteps.searchBook(webDriver,"","searchByTitle");
        assertThat(webDriver.findElement(By.id("error")).getText(),is("Please input a value for your search, and try again."));
    }

    @Test
    public void shouldSearchByAuthor(){
        CommonSteps.searchBook(webDriver,"Paulo Coelho","searchByAuthor");
        assertThat(webDriver.findElement(By.className("book-list")).isDisplayed(), is(true));
    }

    @Test
    public void shouldDisplayTheSortOrderOfResults(){
        CommonSteps.searchBook(webDriver,"Agile Samurai","searchByTitle");
        assertEquals("Your search was sorted by relevance.", webDriver.findElement(By.tagName("p")).getText());
    }

    @After
    public void tearDown() {
        webDriver.close();
    }


}
