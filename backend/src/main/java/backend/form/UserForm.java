package backend.form;

//InputBoundary Layer

import javax.validation.constraints.*;

public class UserForm {
    public static class UserRegisterForm {
        @Size(max = 20)
        @NotBlank
        public String name;
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$")
        public String password;
        @Email
        public String email;
        public String phone;

        public UserRegisterForm(){

        }
    }
}
