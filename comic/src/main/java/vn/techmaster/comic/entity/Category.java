package vn.techmaster.comic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "category")
@Table(name = "category")
@Data
@NoArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;

    public Category(String category) {
        this.category = category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Story story;


}
