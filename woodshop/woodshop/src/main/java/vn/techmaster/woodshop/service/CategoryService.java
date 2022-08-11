package vn.techmaster.woodshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import vn.techmaster.woodshop.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public Optional<Category> findById(String id);
    Page<Category> findAll(Pageable pageable);
    List<Category> findAll(Sort sort);
    List<Category> findAll() ;
    Page<Category> findByNameContaining(String name, Pageable pageable);
    List<Category> findByNameContaining (String name ) ;
    <S extends Category> S save(S entity);
    public Category addCategory(Category category) ;
    public void deleteCategory (String id) ;
    boolean existsByName(String name);
    boolean existsByCode(String name);
    boolean existsByNameOrCode(String name, String code);
}
