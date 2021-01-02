package com.freshvotes.web;

import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private ProductRepository productRepo;

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String rootView(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboardView(@AuthenticationPrincipal User user, ModelMap model){
        List<Product> products = productRepo.findByUser(user);
        model.put("products", products);
        return "dashboard";
    }
}