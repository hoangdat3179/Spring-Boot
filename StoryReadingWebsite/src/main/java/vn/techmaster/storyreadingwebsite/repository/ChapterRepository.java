package vn.techmaster.storyreadingwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.techmaster.storyreadingwebsite.entity.Chapter;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    @Query("select c from chapter c where c.story.id = ?1")
    List<Chapter> findByStoryId(Long bId);

    @Query("select c from chapter c where c.story.id = ?1 and c.id = ?2")
    Optional<Chapter> findChapterByStoryIdAndChapterId(Long bId, Long chId);



}