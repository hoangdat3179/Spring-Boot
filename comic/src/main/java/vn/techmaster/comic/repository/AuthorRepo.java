package vn.techmaster.comic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.comic.model.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

}

