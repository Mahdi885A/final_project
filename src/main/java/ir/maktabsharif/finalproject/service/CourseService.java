package ir.maktabsharif.finalproject.service;

import ir.maktabsharif.finalproject.dto.request.CourseRequest;
import ir.maktabsharif.finalproject.dto.response.CourseResponse;
import ir.maktabsharif.finalproject.mapper.CourseMapper;
import ir.maktabsharif.finalproject.model.Course;
import ir.maktabsharif.finalproject.model.Student;
import ir.maktabsharif.finalproject.model.Teacher;
import ir.maktabsharif.finalproject.repository.CourseRepository;
import ir.maktabsharif.finalproject.repository.StudentRepository;
import ir.maktabsharif.finalproject.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseMapper courseMapper;

    public CourseResponse create(CourseRequest request) {

        if (courseRepository.existsByCourseCode(request.getCourseCode())) {
            throw new RuntimeException("Course code already exists");
        }

        Course course = courseMapper.toEntity(request);

        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Transactional(readOnly = true)
    public CourseResponse findById(Long id) {

        Course course = findCourseEntity(id);

        return courseMapper.toResponse(course);
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> findAll() {

        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    public CourseResponse update(Long id, CourseRequest request) {

        Course course = findCourseEntity(id);

        if (!course.getCourseCode().equals(request.getCourseCode())
                && courseRepository.existsByCourseCode(request.getCourseCode())) {
            throw new RuntimeException("Course code already exists");
        }

        courseMapper.updateEntity(course, request);

        return courseMapper.toResponse(courseRepository.save(course));
    }

    public void delete(Long id) {

        Course course = findCourseEntity(id);

        courseRepository.delete(course);
    }

    public CourseResponse assignTeacher(Long courseId, Long teacherId) {

        Course course = findCourseEntity(courseId);

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (!teacher.isApproved()) {
            throw new RuntimeException("Teacher is not approved");
        }

        course.setTeacher(teacher);

        return courseMapper.toResponse(courseRepository.save(course));
    }

    public CourseResponse addStudent(Long courseId, Long studentId) {

        Course course = findCourseEntity(courseId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (!student.isApproved()) {
            throw new RuntimeException("Student is not approved");
        }

        if (!course.getStudents().contains(student)) {
            course.getStudents().add(student);
        }

        return courseMapper.toResponse(courseRepository.save(course));
    }

    public CourseResponse removeStudent(Long courseId, Long studentId) {

        Course course = findCourseEntity(courseId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        course.getStudents().remove(student);

        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Transactional(readOnly = true)
    public List<Student> getStudents(Long courseId) {

        Course course = findCourseEntity(courseId);

        return course.getStudents();
    }

    @Transactional(readOnly = true)
    public Teacher getTeacher(Long courseId) {

        Course course = findCourseEntity(courseId);

        return course.getTeacher();
    }

    private Course findCourseEntity(Long id) {

        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}