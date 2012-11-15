package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

//Understands that response to url "viewbook" and send commands to its associated view
@Controller
public class BookViewController {
    public static final String COULD_NOT_FIND_BOOK = "Could not find book";
    public static final String RECOMMENDED_SUCCESFULLY = "Book was recommended successfully";
    private BookService bookService;

    @Autowired
    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/viewbook", method = RequestMethod.GET)

    public ModelAndView viewBook(@RequestParam(value = "bookId", defaultValue = "0") String bookId,
                                 @RequestParam(value = "notification", defaultValue = "") String notification) {
        ModelAndView modelAndView = new ModelAndView("viewbook");
        Book book = getBookByID(bookId);
        if (book == null) {
            return modelAndView.addObject("bookNotFound", COULD_NOT_FIND_BOOK);
        }
        modelAndView.addObject("notification", notification);
        return modelAndView.addObject("book", book);
    }

    private Book getBookByID(String bookId) {
        try {
            return bookService.getBookByID(Integer.parseInt(bookId));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @RequestMapping(value = "/recommend", method = RequestMethod.POST)
    public RedirectView recommend(@RequestParam(value = "bookId", defaultValue = "") int bookId) {
        Book book = bookService.getBookByID(bookId);
        if (book == null) {
            return new RedirectView("/viewbook?bookId=", true);
        }
        bookService.updateRecommendCount(book);
        return new RedirectView("/viewbook?bookId=" + book.getId() + "&notification=" + RECOMMENDED_SUCCESFULLY, true);
    }
}
