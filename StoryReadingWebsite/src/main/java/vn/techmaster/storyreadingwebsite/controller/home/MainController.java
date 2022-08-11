package vn.techmaster.storyreadingwebsite.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.techmaster.storyreadingwebsite.Service.StoryService;
import vn.techmaster.storyreadingwebsite.entity.Story;
import vn.techmaster.storyreadingwebsite.entity.Category;
import vn.techmaster.storyreadingwebsite.entity.Chapter;
import vn.techmaster.storyreadingwebsite.repository.StoryRepository;
import vn.techmaster.storyreadingwebsite.repository.CategoryRepository;
import vn.techmaster.storyreadingwebsite.repository.ChapterRepository;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private StoryRepository storyRepo;
    @Autowired
    private StoryService storyService;
    @Autowired
    private ChapterRepository chapterRepo;

    @Autowired
    private CategoryRepository categoryRepo;


    @GetMapping("/")
    public String home(Model model){
        List<Category> listCategories = categoryRepo.findAll();
        List<Story> listStories = storyRepo.findAll();
        model.addAttribute("listStories",listStories);
        model.addAttribute("listCategories",listCategories);
        return "index";
    }


    @GetMapping(value = "/stories/{bId}")
    public String showBookDetailByID(Model model, @PathVariable("bId") Long bId) {
        Story story = storyRepo.findById(bId).get();
        List<Category> listCategories = categoryRepo.findAll();
        List<Chapter> listChapters = chapterRepo.findByStoryId(bId);
        model.addAttribute("listChapters",listChapters);
        model.addAttribute("story", story);
        model.addAttribute("listCategories",listCategories);
        return "bookDetail";
    }

    @GetMapping(value = "/stories/{bId}/chapter/{chId}")
    public String showChapter(Model model, @PathVariable("bId") Long bId, @PathVariable("chId") Long chId) {
        Optional<Chapter> chapter = chapterRepo.findChapterByStoryIdAndChapterId(bId,chId);
        List<Category> listCategories = categoryRepo.findAll();
        Story story = storyRepo.findById(bId).get();
        List<Chapter> listChapters = chapterRepo.findByStoryId(bId);
        model.addAttribute("chapter", chapter.get());
        model.addAttribute("listChapters",listChapters);
        model.addAttribute("listCategories",listCategories);
        model.addAttribute("story", story);
        return "chapterBook";
    }

    @RequestMapping("/search")
    public String searchByKeyword(Model model, String keyword){
        List<Category> listCategories = categoryRepo.findAll();
        if(keyword!=null){
            List<Story> listStories = storyService.findByTitleContainsIgnoreCase(keyword);
            model.addAttribute("listStories", listStories);
        }else {
            List<Story> listStories = storyRepo.findAll();
            model.addAttribute("listStories", listStories);
        }
        model.addAttribute("listCategories",listCategories);

        return "bookCategory";
    }

    @RequestMapping("/stories/category/{cId}")
    public String findBookByCategory(Model model,@PathVariable("cId") Story cId){
        List<Category> listCategories = categoryRepo.findAll();
        Optional<Category> category = categoryRepo.findById(cId.getId());
        List<Story> listStories = storyRepo.findAllByCategoryId(cId.getId());
        model.addAttribute("category", category);
        model.addAttribute("listStories", listStories);
        model.addAttribute("listCategories",listCategories);
        return "bookCategory";
    }




}
