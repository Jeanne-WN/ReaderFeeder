package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//Job: Understands interaction with book table in DB
public interface BookMapper {
    @Insert("INSERT INTO books (title, author, image, description, ISBN10, ISBN13) VALUES(#{title}, #{author}, #{image}," +
            " #{description}, #{ISBN10}, #{ISBN13})")
    void insertBook(Book book);

    @Select("SELECT * FROM books WHERE title LIKE #{title}")
    Book getBookByTitle(String title);

    @Select("SELECT * FROM books WHERE title LIKE #{title}")
    List<Book> getBooksByTitle(String title);
}
