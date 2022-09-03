package vn.techmaster.storyreadingwebsite.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.techmaster.storyreadingwebsite.Service.StoryService;
import vn.techmaster.storyreadingwebsite.Service.UserService;
import vn.techmaster.storyreadingwebsite.entity.Comment;
import vn.techmaster.storyreadingwebsite.entity.Story;
import vn.techmaster.storyreadingwebsite.entity.User;
import vn.techmaster.storyreadingwebsite.repository.CommentRepository;
import vn.techmaster.storyreadingwebsite.repository.StoryRepository;

import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private StoryRepository storyRepo;

    // Thêm comment vào truyện

    @PostMapping("/stories/{sId}")
    public String saveChapter(Comment comment, @PathVariable("sId") Long id, @AuthenticationPrincipal Authentication authentication){
        User user = userService.getLogin(authentication);
        Story storyOptional = storyService.findById(id).get();
        comment.setUser(user);
        comment.setStory(storyOptional);
        commentRepository.save(comment);
        return "redirect:/stories/" + storyOptional.getId();
    }

    //Xóa Comment
    @GetMapping("/stories/{sID}/comment/delete/{id}")
    public String deleteChapter(@PathVariable("id") Long id,@PathVariable("sID")Long sId){
        Story story = storyRepo.findById(sId).get();
        commentRepository.deleteById(id);
        return "redirect:/stories/" + story.getId();
    }

}
