package vn.techmaster.storyreadingwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.techmaster.storyreadingwebsite.entity.StoryLike;

import java.util.Optional;

@Repository
public interface StoryUserRepository extends JpaRepository<StoryLike,Long> {
    @Query("select s from story_like s where s.story.id = ?1 and s.user.id = ?2")
    Optional<StoryLike> findByStoryIdAndUserId(Long sId, Long uId);


}

