package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public ModelAndView redirect(HttpServletRequest httpServletRequest) {
        String remoteUser = httpServletRequest.getRemoteUser();
        ModelAndView modelAndView = new ModelAndView("welcome");
        User user = userService.getUserByCasname(remoteUser);
        if(userService.isUserExisted(remoteUser)) {
            modelAndView.addObject("user",user);
            return modelAndView;
        }
        else
            return new ModelAndView("create-user-profile");
    }
}
