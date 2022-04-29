package vn.techmaster.demosession.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.techmaster.demosession.exception.UserException;
import vn.techmaster.demosession.request.LoginRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping
    public String showHomePage(){
        return "index";
    }
    @GetMapping("login")
    public String showLogin(Model model) {
        model.addAttribute("loginrequest", new LoginRequest("", ""));
        return "login";
    }
    @PostMapping("login")
    public String handleLogin(@Valid @ModelAttribute("loginrequest") LoginRequest loginRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("register")
    public String showRegisterForm(){
        return "register";
    }
    @GetMapping("foo")
    public String foo(){
        throw new UserException("User is not found");
    }

    

}
