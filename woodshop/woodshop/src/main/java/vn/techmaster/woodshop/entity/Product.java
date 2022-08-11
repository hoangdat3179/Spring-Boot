package vn.techmaster.woodshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
@Table(name = "product")
@Builder
public class Product implements Serializable {

    @GenericGenerator(name = "random_id", strategy = "vn.techmaster.woodshop.model.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "name", columnDefinition = "nvarchar(100) not null")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "image", length = 200)
    private String image;

    @Column(name = "description", columnDefinition = "nvarchar(500)")
    private String description;

    @Column(name = "discount")
    private double discount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date created_at;

    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    private Date update_at;


    //Liên kết category 1 - n product
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;

    // Liên kết 1 product - n galery
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference
    private List<Galery> galeries = new ArrayList<>();

    // liên kết 1 - n product cart_product
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "Product_id")
    private List<Cart_Product> cart_products = new ArrayList<>();

    // Liên kết 1 product - n order_detail
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference
    @JsonIgnore
    private List<Order_detail> order_details = new ArrayList<>();


    public Product(String name, int quantity, double price, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public void add(Order_detail order_detail) {
        order_detail.setProduct(this);
        order_details.add(order_detail);
    }

    public void add(Galery galery) {
        galery.setProduct(this);
        galeries.add(galery);
    }

    public void add(Cart_Product cart_product) {
        cart_product.setProduct(this);
        cart_products.add(cart_product);
    }


}

