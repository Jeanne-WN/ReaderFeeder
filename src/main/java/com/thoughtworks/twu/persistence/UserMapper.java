package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper {

    @Insert("insert into users (casname, name) values (#{casname}, #{name})")
    void insertUser(User user);


    @Select("select * from users where casname=#{casname}")
    User getUserByCasname(String casname);


    @Insert("insert into readings (user_casname, book_id, reading_status) values (#{casname}, #{bookId}, 1)")
    void markBookAsWantToRead(@Param("casname") String casname, @Param("bookId")int bookId);

    @Select("select books.* from readings, books where user_casname=#{casname} AND book_id = books.id AND reading_status=1")
    List<Book> getWantToReadListByUserCasname(String casname);
}
