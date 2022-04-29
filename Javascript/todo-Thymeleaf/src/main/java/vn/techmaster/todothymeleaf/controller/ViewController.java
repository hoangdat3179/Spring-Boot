package vn.techmaster.todothymeleaf.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.techmaster.todothymeleaf.service.TodoService;

@Controller
@AllArgsConstructor
public class ViewController {

    private final TodoService todoService;

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("todos", todoService.getTodos(""));
        return "index";
    }
}
