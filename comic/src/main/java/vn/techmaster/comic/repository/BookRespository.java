package vn.techmaster.comic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.comic.entity.Book;

public interface BookRespository extends JpaRepository<Book, Long> {
  
}
