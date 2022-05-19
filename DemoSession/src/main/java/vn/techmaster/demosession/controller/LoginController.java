package vn.techmaster.demosession.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demosession.dto.UserDTO;
import vn.techmaster.demosession.exception.UserException;
import vn.techmaster.demosession.model.User;
import vn.techmaster.demosession.repository.UserRepo;
import vn.techmaster.demosession.request.LoginRequest;
import vn.techmaster.demosession.request.RegisterRequest;
import vn.techmaster.demosession.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired UserService userService;
    @Autowired
    UserRepo userRepository;

    @GetMapping
    public String showHomePage(Model model, HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        System.out.println(session.getId());
        if (userDTO != null) {
            model.addAttribute("user", userDTO);
        }
        return "index";
    }

    @GetMapping("login")
    public String showLogin(Model model) {
        model.addAttribute("loginrequest", new LoginRequest("", ""));
        return "login";
    }
    @PostMapping("login")
    public String handleLogin(@Valid @ModelAttribute("loginrequest") LoginRequest loginRequest,
                              BindingResult result,
                              HttpSession session) {
        if (result.hasErrors()) {
            return "login";
        }
        User user;
        try {
            user = userService.login(loginRequest.email(), loginRequest.password());
            session.setAttribute("user", new UserDTO(user.getId(), user.getFullName(), user.getEmail()));
            return "redirect:/";
        } catch (UserException ex) {
            switch (ex.getMessage()) {
                case "User is not found":
                    result.addError(new FieldError("loginrequest", "email", "Email does not exist"));
                    break;
                case "User is not activated":
                    result.addError(new FieldError("loginrequest", "email", "User is not activated"));
                    break;
                case "Password is incorrect":
                    result.addError(new FieldError("loginrequest", "password", "Password is incorrect"));
                    break;
            }
            return "login";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("register")
    public String showRegisterForm(){
        return "register";
    }
    @GetMapping("/register")
    public String HomeRegister(Model model) {
        model.addAttribute("register",new RegisterRequest("","","",""));
        return "register";
    }
    @GetMapping("/admin")
    public String ShowAdminRegister(HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if(userDTO!=null){
            return "admin";
        }
        return "redirect:/";
    }
    @PostMapping("/register")
    public String Register(@Valid @ModelAttribute("register") RegisterRequest registerRequest, BindingResult result){
        if(!registerRequest.password().equals(registerRequest.retypePassword())){
            result.addError(new FieldError("register", "retypePassword", "Mật khẩu không trùng nhau"));
            return "register";
        }
        if (result.hasErrors()) {
            return "register";
        }
        User user;
        try {
            userService.addUser(registerRequest.name(),registerRequest.email(),registerRequest.password());
        }catch (UserException e){
            result.addError(new FieldError("register", "email", e.getMessage()));
            return "register";
        }
        return "redirect:/";
    }
    @GetMapping("foo")
    public String foo(){
        throw new UserException("User is not found");
    }

    @GetMapping("/validate/{register-code}")
    public String validateUser(@PathVariable("register-code")String code ){
        userRepository.checkValidate(code);
        return "index";
    }
    

}
