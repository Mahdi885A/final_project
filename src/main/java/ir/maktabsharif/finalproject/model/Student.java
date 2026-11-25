package ir.maktabsharif.finalproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User {
    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

}
