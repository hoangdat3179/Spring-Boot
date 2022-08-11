package vn.techmaster.woodshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/")
    public String homePage () {
        return "client/index" ;
    }

}
