package ir.maktabsharif.finalproject.controller;

import ir.maktabsharif.finalproject.dto.request.CourseRequest;
import ir.maktabsharif.finalproject.dto.response.CourseResponse;
import ir.maktabsharif.finalproject.model.Student;
import ir.maktabsharif.finalproject.model.Teacher;
import ir.maktabsharif.finalproject.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // Create course
    @PostMapping
    public ResponseEntity<CourseResponse> create(
            @Valid @RequestBody CourseRequest request) {

        CourseResponse response = courseService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all courses
    @GetMapping
    public ResponseEntity<List<CourseResponse>> findAll() {

        return ResponseEntity.ok(courseService.findAll());
    }

    // Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(courseService.findById(id));
    }

    // Update course
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequest request) {

        return ResponseEntity.ok(courseService.update(id, request));
    }

    // Delete course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        courseService.delete(id);

        return ResponseEntity.noContent().build();
    }

    // Assign teacher to course
    @PutMapping("/{courseId}/teacher/{teacherId}")
    public ResponseEntity<CourseResponse> assignTeacher(
            @PathVariable Long courseId,
            @PathVariable Long teacherId) {

        return ResponseEntity.ok(
                courseService.assignTeacher(courseId, teacherId)
        );
    }

    // Add student to course
    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<CourseResponse> addStudent(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                courseService.addStudent(courseId, studentId)
        );
    }

    // Remove student from course
    @DeleteMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<CourseResponse> removeStudent(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                courseService.removeStudent(courseId, studentId)
        );
    }

    // Get course students
    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<Student>> getStudents(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(courseService.getStudents(courseId));
    }

    // Get course teacher
    @GetMapping("/{courseId}/teacher")
    public ResponseEntity<Teacher> getTeacher(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(courseService.getTeacher(courseId));
    }
}


