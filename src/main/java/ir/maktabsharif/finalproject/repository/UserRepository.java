package ir.maktabsharif.finalproject.repository;

import ir.maktabsharif.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}