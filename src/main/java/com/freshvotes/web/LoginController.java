package com.freshvotes.web;

import com.freshvotes.domain.User;
import com.freshvotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

//    @RequestMapping(value ="/login", method = RequestMethod.GET)
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.put("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost (User user){
        userService.save(user);
//        System.out.println(user.toString());
        return "redirect:/login";
    }
}
