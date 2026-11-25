package ir.maktabsharif.finalproject.service;

import ir.maktabsharif.finalproject.dto.request.RegisterStudentRequest;
import ir.maktabsharif.finalproject.dto.response.UserResponse;
import ir.maktabsharif.finalproject.mapper.UserMapper;
import ir.maktabsharif.finalproject.model.Student;
import ir.maktabsharif.finalproject.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterStudentRequest request) {

        if (studentRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (request.getPhone() != null &&
                studentRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }

        Student student = userMapper.toStudent(request);

        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setApproved(false);

        Student savedStudent = studentRepository.save(student);

        return userMapper.toStudentResponse(savedStudent);
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return userMapper.toStudentResponse(student);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {

        return studentRepository.findAll()
                .stream()
                .map(userMapper::toStudentResponse)
                .toList();
    }

    public UserResponse approve(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setApproved(true);

        return userMapper.toStudentResponse(studentRepository.save(student));
    }

    public UserResponse update(Long id, RegisterStudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setPhone(request.getPhone());

        return userMapper.toStudentResponse(studentRepository.save(student));
    }

    public void delete(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }

        studentRepository.deleteById(id);
    }
}