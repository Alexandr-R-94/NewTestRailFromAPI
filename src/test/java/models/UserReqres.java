package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserReqres {
    private String name;
    private String job;
    private String email;
    private String password;
}
