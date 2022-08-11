package vn.techmaster.storyreadingwebsite.controller.home;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.techmaster.storyreadingwebsite.Service.UserServices;
import vn.techmaster.storyreadingwebsite.entity.User;

@Controller
public class RegisterController {


    @Autowired
    private UserServices userServices;



    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user",new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user){
        userServices.registerDefaultUser(user);
        return "register_success";
    }

}
