package ir.maktabsharif.finalproject.mapper;

import ir.maktabsharif.finalproject.dto.request.RegisterStudentRequest;
import ir.maktabsharif.finalproject.dto.request.RegisterTeacherRequest;
import ir.maktabsharif.finalproject.dto.response.UserResponse;
import ir.maktabsharif.finalproject.model.Student;
import ir.maktabsharif.finalproject.model.Teacher;
import ir.maktabsharif.finalproject.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public Student toStudent(RegisterStudentRequest request) {

        Student student = new Student();

        student.setUsername(request.getUsername());
        student.setPassword(request.getPassword());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setPhone(request.getPhone());
        student.setApproved(false);

        return student;
    }

    public Teacher toTeacher(RegisterTeacherRequest request) {

        Teacher teacher = new Teacher();

        teacher.setUsername(request.getUsername());
        teacher.setPassword(request.getPassword());
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setPhone(request.getPhone());
        teacher.setApproved(false);

        return teacher;
    }

    public UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhone(user.getPhone());
        response.setApproved(user.isApproved());

        return response;
    }

    public UserResponse toStudentResponse(Student student) {
        return toResponse(student);
    }

    public UserResponse toTeacherResponse(Teacher teacher) {
        return toResponse(teacher);
    }
}