package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

//Job: Ensure that BookMapper can interact with DB as expected

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true)
@TestExecutionListeners({TransactionalTestExecutionListener.class})
@Transactional
public class BookMapperTest extends IntegrationTest{
    @Autowired
    private BookMapper bookMapper;
    private Book book;

    @Before
    public void setUp() {
        book = new Book("author", "title", "image_src", "description", "0156027321", "978-0156027328");
    }

    @Test
    public void shouldGetBookByTitle() {
        bookMapper.insertBook(book);
        Book result = bookMapper.getBookByTitle("title");
        assertThat(result, equalTo(book));
    }

    @Test
    public void shouldReturnAListOfBooksWithSameTitle(){
        bookMapper.insertBook(book);
        bookMapper.insertBook(book);
        List<Book> bookList = bookMapper.getBooksByTitle(book.getTitle());
        assertThat(bookList.size(), is(2));
    }

    @Transactional
    @Test
    public void shouldGetBookById() {
        Book expected = new Book("J.K. Rowling", "Harry Potter", "http://ecx.images-amazon.com/images/I/51HVlrefdkL._SL500_AA300_.jpg", "fantasy novel", "1234567890", null);
        Book result = bookMapper.getBookById(1);
        assertThat(result, equalTo(expected));
    }
}
