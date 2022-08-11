package vn.techmaster.comic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.*;

@Entity(name = "story")
@Table(name = "story")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

//    @Column(name = "images", nullable = false)
//    private String images;
//
//    @Column(name = "author", nullable = false)
//    private String author;
//
//    private Status status;
//
//    @OneToMany(mappedBy = "story",cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "category_id")
//    private List<Category> categories = new ArrayList<>();
//
//    @OneToMany(mappedBy = "story",cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "chapter_id")
//    private List<Chapter> chapters = new ArrayList<>();

}
