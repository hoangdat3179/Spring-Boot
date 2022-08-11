package vn.techmaster.storyreadingwebsite.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "story_like")
@Table(name = "story_like")
@Data
@NoArgsConstructor
public class StoryLike {
    @Id
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "story_id")
    private Story story;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public StoryLike(Story story, User user) {
        this.story = story;
        this.user = user;
    }
}
