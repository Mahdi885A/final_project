package ir.maktabsharif.finalproject.repository;

import ir.maktabsharif.finalproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByUsername(String username);

    boolean existsByPhone(String phone);
}