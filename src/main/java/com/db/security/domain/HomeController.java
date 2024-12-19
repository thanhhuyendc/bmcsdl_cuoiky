package com.db.security.domain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public String routingHome(@ModelAttribute("userPrincipal") String userPrincipal, Model model) {
        System.out.println(userPrincipal);
        if (userPrincipal.isEmpty()) {
            return "redirect:/user/search";
        } else {
            model.addAttribute("keyword", userPrincipal);
            return "redirect:/user/view-all";
        }
    }
}
