package backend.form;

//InputBoundary Layer

import javax.validation.constraints.*;

public class UserForm {
    public static class UserRegisterForm {

        //input name has maximum length 20, and cannot be empty or white spaces.
        @Size(max = 20)
        @NotBlank
        public String name;

        //input email must follow formal email format.
        @Email
        public String email;

        //input password must contain at least one number, one lower-case letter, one upper-case letter,
        // no white spaces, and has minimum length of 8 and maximum length of 20.
        @NotBlank
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$")
        public String password;

//        //input repeatPassword must not be empty.
//        @NotBlank
//        public String repeatPassword;

        //input phone must be a sequence of 10 numbers.
        @NotBlank
        @Pattern(regexp = "^\\d{10}$")
        public String phone;

        public UserRegisterForm(){

        }
    }
}
