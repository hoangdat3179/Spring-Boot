package vn.techmaster.storyreadingwebsite.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.storyreadingwebsite.entity.Story;
import vn.techmaster.storyreadingwebsite.entity.StoryLike;
import vn.techmaster.storyreadingwebsite.repository.StoryUserRepository;

import java.util.Optional;

@Service
public class StoryLikeService {
    @Autowired
    private StoryUserRepository repo;

    public Optional<StoryLike> addFavoriteStory(Long sId,Long uId){
        Optional<StoryLike> favorite = repo.findByStoryIdAndUserId(sId,uId);
        return favorite;
    }
}
