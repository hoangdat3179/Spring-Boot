package vn.techmaster.storyreadingwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.storyreadingwebsite.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  
}
