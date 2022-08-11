package vn.techmaster.comic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.comic.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}
