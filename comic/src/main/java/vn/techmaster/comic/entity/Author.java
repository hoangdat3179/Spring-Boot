package vn.techmaster.comic.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table
public class Author {
    @Id
    private UUID id;
    private String name;

}
