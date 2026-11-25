package ir.maktabsharif.finalproject.service;

import ir.maktabsharif.finalproject.dto.request.RegisterTeacherRequest;
import ir.maktabsharif.finalproject.dto.response.UserResponse;
import ir.maktabsharif.finalproject.mapper.UserMapper;
import ir.maktabsharif.finalproject.model.Teacher;
import ir.maktabsharif.finalproject.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterTeacherRequest request) {

        if (teacherRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (request.getPhone() != null &&
                teacherRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }

        Teacher teacher = userMapper.toTeacher(request);

        teacher.setPassword(passwordEncoder.encode(request.getPassword()));
        teacher.setApproved(false);

        Teacher savedTeacher = teacherRepository.save(teacher);

        return userMapper.toTeacherResponse(savedTeacher);
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        return userMapper.toTeacherResponse(teacher);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {

        return teacherRepository.findAll()
                .stream()
                .map(userMapper::toTeacherResponse)
                .toList();
    }

    public UserResponse approve(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.setApproved(true);

        return userMapper.toTeacherResponse(teacherRepository.save(teacher));
    }

    public UserResponse update(Long id, RegisterTeacherRequest request) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setPhone(request.getPhone());

        return userMapper.toTeacherResponse(teacherRepository.save(teacher));
    }

    public void delete(Long id) {

        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found");
        }

        teacherRepository.deleteById(id);
    }
}