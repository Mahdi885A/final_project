package ir.maktabsharif.finalproject.mapper;

import ir.maktabsharif.finalproject.dto.request.CourseRequest;
import ir.maktabsharif.finalproject.dto.response.CourseResponse;
import ir.maktabsharif.finalproject.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequest request) {

        Course course = new Course();

        course.setTitle(request.getTitle());
        course.setCourseCode(request.getCourseCode());
        course.setStartDate(request.getStartDate());
        course.setEndDate(request.getEndDate());

        return course;
    }

    public void updateEntity(Course course, CourseRequest request) {

        course.setTitle(request.getTitle());
        course.setCourseCode(request.getCourseCode());
        course.setStartDate(request.getStartDate());
        course.setEndDate(request.getEndDate());
    }

    public CourseResponse toResponse(Course course) {

        CourseResponse response = new CourseResponse();

        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setCourseCode(course.getCourseCode());
        response.setStartDate(course.getStartDate());
        response.setEndDate(course.getEndDate());

        if (course.getTeacher() != null) {
            response.setTeacherId(course.getTeacher().getId());

            response.setTeacherName(
                    course.getTeacher().getFirstName() + " " +
                            course.getTeacher().getLastName()
            );
        }

        return response;
    }
}