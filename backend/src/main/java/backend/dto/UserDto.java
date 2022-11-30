package backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("loginStatus")
    private Boolean loginStatus;

    @JsonProperty("joinedTime")
    private Timestamp joinedTime;

    @JsonProperty("lastActiveTime")
    private Timestamp lastActiveTime;

    @JsonProperty("studentProfile")
    private StudentProfileDto studentProfile;
}
