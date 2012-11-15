package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"user"})
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView redirect(HttpServletRequest httpServletRequest) {
        String remoteUser = httpServletRequest.getRemoteUser();
        ModelAndView modelAndView = new ModelAndView(new RedirectView("welcome",true));
        User user = userService.getUserByCasname(remoteUser);

        if(userService.isUserExisted(remoteUser)) {
            modelAndView.addObject("user",user);
            return modelAndView;
        }
        else
            return new ModelAndView("createUserProfile");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(HttpServletRequest request) {
        String casname = request.getRemoteUser();
        String username = (String) request.getParameter("username");
        if(username.trim().isEmpty()){
            return new ModelAndView("createUserProfile");
        }
        User user = new User(casname, username);
        if(!userService.isUserExisted(user.getCasname())){
            userService.createUser(user);
        }

        ModelAndView modelAndView = new ModelAndView(new RedirectView("welcome",true));
        modelAndView.addObject("user", user);
        return modelAndView ;
    }
}
