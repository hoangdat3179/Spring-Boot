package vn.techmaster.woodshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.techmaster.woodshop.entity.Category;
import vn.techmaster.woodshop.entity.Product;
import vn.techmaster.woodshop.exception.NotFoundException;
import vn.techmaster.woodshop.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;


@Service
public class ProductServiceIpml implements ProductService{

    @PersistenceContext
    private EntityManager em;

    @Autowired private ProductRepository productRepository ;

    @Override
    public Collection<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Category category, Product product) {
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public Optional<Product> findById(String id) {
        Optional<Product> product = productRepository.findById(id) ;
        if(product.isPresent()) {
            return product;
        }
        throw new NotFoundException("Could not find any product with id " + id) ;
    }

    @Override
    public void updateImage(String id, String image) {
        Optional<Product> product = findById(id);
        product.get().setImage(image);
        productRepository.save(product.get());
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    @Override
    public Page<Product> findByNameContaining(String keyword, Pageable pageable) {
        return productRepository.findByNameContaining(keyword, pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

}
