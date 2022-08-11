package vn.techmaster.woodshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_product")
public class Cart_Product {
    @GenericGenerator(name = "random_id", strategy = "vn.techmaster.woodshop.model.RandomIdGenerator")
    @Id
    @GeneratedValue(generator="random_id")
    private String id ;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnore
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnore
    private Product product;

    public Cart_Product(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
    }
}
