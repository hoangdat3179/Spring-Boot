package vn.techmaster.comic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.comic.entity.Story;
import vn.techmaster.comic.repository.StoryRepo;



@Controller
@RequestMapping(value = "/")
public class StoryController {
    @Autowired
    private StoryRepo storyRepo;


    @GetMapping("/story")
    public String listAllStories(Model model) {
        model.addAttribute("stories", storyRepo.findAll());
        return "stories";
    }

    @GetMapping(value = "/{id}")
    public String showStoryDetailByID(Model model, @PathVariable Long id) {
        model.addAttribute("story", storyRepo.findById(id));
        return "story";
    }

    @GetMapping(value = "/add")
    public String addStoryForm(Model model) {
        model.addAttribute("story", new Story());
        return "story_add";
    }
}
