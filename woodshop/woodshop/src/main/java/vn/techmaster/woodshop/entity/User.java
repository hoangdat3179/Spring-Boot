package vn.techmaster.woodshop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Builder
public class User implements Serializable {
    @GenericGenerator(name = "random_id", strategy = "vn.techmaster.woodshop.model.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "random_id")
    private String id;

    @Column(name = "name", columnDefinition = "nvarchar(50)")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


//    @Column(name = "state")
//    @Enumerated(EnumType.STRING)
//    private State state;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;

//    // Liên kết 1 cus - n order
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Orders> orders = new ArrayList<>();


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cart_id")
//    private Cart cart ;

    public User(String name, String email, String password,  Collection<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String email, String password, Collection<? extends GrantedAuthority> mapRolesToAuthorities) {
    }

//    public void add(Orders orderss) {
//        orderss.setUser(this);
//        orders.add(orderss);
//    }
//
//    public void setCart(Cart cart) {
//        cart.setUser(this);
//        this.cart = cart;
//    }
}
