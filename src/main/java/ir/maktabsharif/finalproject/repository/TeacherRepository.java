package ir.maktabsharif.finalproject.repository;

import ir.maktabsharif.finalproject.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    boolean existsByUsername(String username);

    boolean existsByPhone(String phone);
}