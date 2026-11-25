package ir.maktabsharif.finalproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Teacher extends User {
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses = new ArrayList<>();
}