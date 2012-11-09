package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean isUserExisted(String casname) {
        User result = getUserByCasname(casname);
        return result == null? false : true;
    }

    public User getUserByCasname(String casname) {
        return userMapper.getUserByCasname(casname);
    }

    public String createUser(User user) {
        userMapper.insertUser(user);
        return "success";
    }


    public void markBookAsWantToRead(int bookId) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public Book getBookFromWantToReadList(int bookId) {
        return new Book(bookId);
    }
}
