package ir.maktabsharif.finalproject.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean approved;
}