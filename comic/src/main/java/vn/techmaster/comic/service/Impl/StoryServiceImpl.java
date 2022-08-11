package vn.techmaster.comic.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.comic.entity.Story;
import vn.techmaster.comic.exception.NotFoundException;
import vn.techmaster.comic.entity.Category;
import vn.techmaster.comic.entity.Chapter;
import vn.techmaster.comic.repository.StoryRepo;
import vn.techmaster.comic.repository.CategoryRepo;
import vn.techmaster.comic.repository.ChapterRepo;
import vn.techmaster.comic.service.StoryService;

import java.util.List;
import java.util.Optional;


@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    private StoryRepo storyRepo;

    @Override
    public List<Story> findAll() {
        return storyRepo.findAll();
    }

    @Override
    public Optional<Story> findById(Long id) {
        return storyRepo.findById(id);
    }

    @Override
    public Story saveStory(Story story) {
        Story newstory = new Story();
        return storyRepo.save(newstory);
    }

    @Override
    public void updateStory(long id, Story story) {
        Story updatedStory = new Story();
        Optional<Story> optionalBook = storyRepo.findById(id);
        if (optionalBook.isPresent()) {
            storyRepo.save(updatedStory);
        } else {
            throw new NotFoundException("Không tìm thấy truyện");
        }
    }

    @Override
    public void deleteById(long id) {
        Optional<Story> optionalStory = storyRepo.findById(id);
        if (optionalStory.isPresent()) {
            storyRepo.delete(optionalStory.get());
        } else {
            throw new NotFoundException("Không tìm thấy truyện");
        }
    }

}
