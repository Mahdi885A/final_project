package ir.maktabsharif.finalproject.controller;

import ir.maktabsharif.finalproject.dto.request.RegisterStudentRequest;
import ir.maktabsharif.finalproject.dto.response.UserResponse;
import ir.maktabsharif.finalproject.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // Register a new student
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterStudentRequest request) {

        UserResponse response = studentService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Get all students
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {

        List<UserResponse> students = studentService.findAll();

        return ResponseEntity.ok(students);
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable Long id) {

        UserResponse student = studentService.findById(id);

        return ResponseEntity.ok(student);
    }

    // Approve student
    @PutMapping("/{id}/approve")
    public ResponseEntity<UserResponse> approve(
            @PathVariable Long id) {

        UserResponse student = studentService.approve(id);

        return ResponseEntity.ok(student);
    }

    // Update student information
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody RegisterStudentRequest request) {

        UserResponse student = studentService.update(id, request);

        return ResponseEntity.ok(student);
    }

    // Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        studentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}