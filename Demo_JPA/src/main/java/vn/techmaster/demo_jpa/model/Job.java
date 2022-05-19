package vn.techmaster.demo_jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Builder
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id @GeneratedValue private UUID id;
    private String title;
    private String description;
}
