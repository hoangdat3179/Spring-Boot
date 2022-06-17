package vn.techmaster.comic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "book")
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private Long id;
    private String title;
    private String description;
    private String author;
    private Category category;
    private Status status;
    private String view;
    private String releaseDate;
}
