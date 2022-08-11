package vn.techmaster.storyreadingwebsite.APIs;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.storyreadingwebsite.Service.StoryService;
import vn.techmaster.storyreadingwebsite.entity.Story;
import vn.techmaster.storyreadingwebsite.repository.StoryRepository;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StoryAPIController {

    @Autowired
    private StoryRepository storyRepository;


    @Autowired
    private StoryService storyService;

    // Danh sach tất cả truyện
    @Operation(summary = "Danh sách truyện")
    @GetMapping(value = "/story")
    public ResponseEntity<?> listStory() {
        Collection<Story> listStory = storyRepository.findAll();
        return ResponseEntity.ok(listStory);
    }

    // Tìm truyện theo id
    @Operation(summary = "Truyện theo id")
    @GetMapping("/story/{id}")
    public ResponseEntity<?> singerById(@PathVariable Long id) {
        Optional<Story> story = storyRepository.findById(id);
        return ResponseEntity.ok(story);
    }


}
