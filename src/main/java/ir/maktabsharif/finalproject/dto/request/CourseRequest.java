package ir.maktabsharif.finalproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseRequest {

    @NotBlank(message = "Course title is required")
    @Size(max = 100, message = "Course title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Course code is required")
    @Size(max = 30, message = "Course code cannot exceed 30 characters")
    private String courseCode;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;
}