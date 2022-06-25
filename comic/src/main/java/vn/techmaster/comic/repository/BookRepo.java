package vn.techmaster.comic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.comic.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
  
}
