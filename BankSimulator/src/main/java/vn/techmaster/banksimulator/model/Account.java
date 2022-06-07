package vn.techmaster.banksimulator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    private String id;

    public Account(String id, String username, long balance) {
        this.id = id;
        this.username = username;
        this.balance = balance;
    }

    private String  username;
    private long balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


}
