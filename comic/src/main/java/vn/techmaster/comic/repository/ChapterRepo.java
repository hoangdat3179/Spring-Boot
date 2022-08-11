package vn.techmaster.comic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.comic.entity.Chapter;

@Repository
public interface ChapterRepo extends JpaRepository<Chapter, Long> {

}
