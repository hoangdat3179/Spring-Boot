package vn.techmaster.woodshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.techmaster.woodshop.entity.Category;
import vn.techmaster.woodshop.entity.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    Collection<Product> getAllProduct();

    Product addProduct(Category category, Product product);

    <S extends Product> S save(S entity);

    Optional<Product> findById(String id);

    void updateImage(String id, String image);

    void deleteById(String id);

    void delete(Product entity);

    Page<Product> findByNameContaining(String keyword, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

}
