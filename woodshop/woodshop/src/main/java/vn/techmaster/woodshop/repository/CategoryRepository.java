package vn.techmaster.woodshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.woodshop.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Page<Category> findByNameContaining(String name, Pageable pageable);
    List<Category> findByNameContaining(String name);
    boolean existsByNameOrCode(String name, String code);
    boolean existsByName(String name);
    boolean existsByCode(String code);
}
