package vn.techmaster.storyreadingwebsite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "chapter")
@Table(name = "chapter")
@Data
@NoArgsConstructor
public class Chapter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial", nullable = false, precision = 12, scale = 0)
    private Float serial;
    @Column(nullable = false,unique = true)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private Story story;

    public Chapter(String title, String content, Story story) {
        this.title = title;
        this.content = content;
        this.story = story;
    }
}
