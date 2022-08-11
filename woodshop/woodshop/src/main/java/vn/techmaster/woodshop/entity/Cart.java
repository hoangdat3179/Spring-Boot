package vn.techmaster.woodshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "Cart")
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "vn.techmaster.woodshop.model.RandomIdGenerator")
    @Id
    @GeneratedValue(generator="random_id")
    private String id ;


    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = false )
    @JoinColumn(name = "cart_id")
    private List<Cart_Product> cart_products = new ArrayList<>();

    @Enumerated
    private Status status;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    public Cart(Status status) {
        this.status = status;
    }

    public void add (Cart_Product cart_product) {
        cart_product.setCart(this);
        cart_products.add(cart_product);
    }

}
