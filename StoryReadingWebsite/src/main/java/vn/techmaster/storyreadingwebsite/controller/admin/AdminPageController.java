package vn.techmaster.storyreadingwebsite.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vn.techmaster.storyreadingwebsite.repository.CategoryRepository;
import vn.techmaster.storyreadingwebsite.repository.StoryRepository;


@Controller
public class AdminPageController {


    @GetMapping("/admin")
    public String AdminPage(){
        return "fragmentsAdmin";
    }

    @GetMapping("/admin/login")
    public String viewAdminLoginPage() {
        return "admin/admin_login";
    }
    @GetMapping("/user/login")
    public String viewUserLoginPage() {
        return "user/user_login";
    }
}
