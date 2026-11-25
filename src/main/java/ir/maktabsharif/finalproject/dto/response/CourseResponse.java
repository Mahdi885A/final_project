package ir.maktabsharif.finalproject.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String title;
    private String courseCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long teacherId;
    private String teacherName;
}