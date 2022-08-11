package vn.techmaster.woodshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Order_detail")
@Table(name = "order_detail")
public class Order_detail implements Serializable {

    @GenericGenerator(name = "random_id", strategy = "vn.techmaster.woodshop.model.RandomIdGenerator")
    @Id @GeneratedValue(generator="random_id")
    private String id ;

    @Column(name = "price")
    private double price ;

    @Column(name = "quantity")
    private int quantity ;

    @Column (name = "discount")
    private double discount ;

    //TODO Liên kết với bảng product
    @ManyToOne
    @JoinColumn(name = "product_id"  )
    @JsonBackReference
    private Product product;

    //TODO liên kết với bảng order
    @ManyToOne
    @JoinColumn(name = "order_id" )
    @JsonBackReference
    private Orders orders;

    public Order_detail(double price, int quantity, double discount , Product product , Orders orders) {
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.product = product ;
        this.orders = orders;
    }

}
