package vn.techmaster.woodshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Feeback")
@Table(name = "feeback")
public class Feeback implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "vn.techmaster.woodshop.model.RandomIdGenerator")
    @Id @GeneratedValue(generator="random_id")
    private String id ;

    @Column (name = "fullname" )
    private String fullname ;

    @Column(name = "email")
    private String email ;

    @Column(name = "title" )
    private String title ;

    @Column(name = "content")
    private String content ;
}
