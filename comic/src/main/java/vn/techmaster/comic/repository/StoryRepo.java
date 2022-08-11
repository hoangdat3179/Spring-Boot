package vn.techmaster.comic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.comic.entity.Category;
import vn.techmaster.comic.entity.Story;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepo extends JpaRepository<Story, Long> {
}
