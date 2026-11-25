package ir.maktabsharif.finalproject.controller;

import ir.maktabsharif.finalproject.dto.request.RegisterTeacherRequest;
import ir.maktabsharif.finalproject.dto.response.UserResponse;
import ir.maktabsharif.finalproject.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterTeacherRequest request) {

        UserResponse response = teacherService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {

        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(teacherService.findById(id));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<UserResponse> approve(
            @PathVariable Long id) {

        return ResponseEntity.ok(teacherService.approve(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody RegisterTeacherRequest request) {

        return ResponseEntity.ok(teacherService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        teacherService.delete(id);

        return ResponseEntity.noContent().build();
    }
}