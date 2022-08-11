package vn.techmaster.woodshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.woodshop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findByNameContaining(String keyword, Pageable pageable);
}
