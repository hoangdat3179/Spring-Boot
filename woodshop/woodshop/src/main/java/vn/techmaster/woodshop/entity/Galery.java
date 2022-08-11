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
@Entity(name = "Galery")
@Table (name = "Galery")
public class Galery implements Serializable {

    @GenericGenerator(name = "random_id", strategy = "vn.techmaster.woodshop.model.RandomIdGenerator")
    @Id @GeneratedValue(generator="random_id")
    private String id ;

    @Column(name = "thumbnail" , columnDefinition = "varchar(500)")
    private String thumbnail ;

    // Liên kết 1 product - n galery
    @ManyToOne
    @JoinColumn(name = "product_id" , nullable = false )
    @JsonBackReference
    private Product product;

    public Galery(String thumbnail, Product product) {
        this.thumbnail = thumbnail;
        this.product = product;
    }
}
