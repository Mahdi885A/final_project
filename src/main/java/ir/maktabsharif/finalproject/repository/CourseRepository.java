package ir.maktabsharif.finalproject.repository;

import ir.maktabsharif.finalproject.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByCourseCode(String courseCode);
}