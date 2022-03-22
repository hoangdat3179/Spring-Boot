package vn.techmaster.myweb.Repository;

import java.util.ArrayList;
import java.util.List;

import vn.techmaster.myweb.model.Student;

public class ListStudent {
    public static List<Student> getStudent() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Đạt",20,"Hà Nội"));
        students.add(new Student(2,"Cường",22,"Vĩnh Phúc"));
        students.add(new Student(3,"Dũng",21,"Hải Dương"));
        students.add(new Student(4,"Hoa",23,"Hà Nội"));
        return students;
    }
}
