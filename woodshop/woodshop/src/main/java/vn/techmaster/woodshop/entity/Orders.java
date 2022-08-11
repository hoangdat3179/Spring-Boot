package vn.techmaster.woodshop.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Orders")
@Table(name = "orders")
public class Orders implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "vn.techmaster.woodshop.model.RandomIdGenerator")
    @Id @GeneratedValue(generator="random_id")
    private String id ;

    @Column(name = "email")
    private String email ;

    @Column(name = "phone" , length = 12)
    private String phone ;

    @Column (name = "address" )
    private String address ;

    @Column (name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date created_at ;

    @Column (name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Date updated_at ;

    // Liên kết 1 order - n order_detail
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL , orphanRemoval = true )
    @JsonManagedReference
    private List<Order_detail> order_details = new ArrayList<>();

    // Liên kết 1 cus - n orders
//    @ManyToOne
//    @JoinColumn(name = "user_id" )
//    @JsonBackReference
//    private User user;

    public Orders(String email, String phone, String address) {
        this.email = email;
        this.phone = phone;
        this.address = address;

    }
    public void add (Order_detail order_detail) {
        order_detail.setOrders(this);
        order_details.add(order_detail);
    }
}
