package vn.techmaster.relation.model.manymany.separate_primary_key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "subject")
@Table(name = "subject")
@Data
@NoArgsConstructor
public class Subject {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  public Subject(String name) {
    this.name = name;
  }

  @OneToMany(mappedBy = "subject")
  @JsonIgnore
  private List<StudentSubject> studentSubjects = new ArrayList<>();

  @JsonGetter(value = "students")
  @Transient
  public Map<String, Float> getStudents() {
    Map<String, Float> studentScore = new HashMap<>();
    studentSubjects.stream().forEach( studentSubject -> {
              studentScore.put(studentSubject.getStudent().getName(), studentSubject.getScore());
            }
    );
    return studentScore;
  }

}
