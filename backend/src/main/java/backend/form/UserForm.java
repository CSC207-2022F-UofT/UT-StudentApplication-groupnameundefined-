package backend.form;

//InputBoundary Layer

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

public class UserForm {

	@Getter
	public static class RegisterForm {

		/**
		 * Name has maximum length 20, and cannot be empty or white spaces.
		 */
		@NotBlank
		@Size(max = 20)
		public String name;

		/**
		 * Email must follow formal email format.
		 */
		@NotBlank
		@Email
		public String email;

		/**
		 * Password must contain at least one number, one lower-case letter, one upper-case letter,
		 * no white spaces, and has minimum length of 8 and maximum length of 20.
		 */
		@NotBlank
		@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$")
		public String password;

		/**
		 * Phone must be a sequence of 10 numbers.
		 */
		@NotBlank
		@Pattern(regexp = "^\\d{10}$")
		public String phone;

	}

	@Getter
	public static class LoginForm {

		@NotBlank
		@Email
		public String email;

		@NotBlank
		public String password;

	}
}
