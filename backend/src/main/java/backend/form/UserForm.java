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
		@Size(min = 2, max = 20, message = "Name must be between 2-20 characters")
		public final String name;

		/**
		 * Email must follow formal email format.
		 */
		@NotBlank
		@Email(message = "Not a valid email")
		public final String email;

		/**
		 * Password must contain at least one number, one lower-case letter, one upper-case letter,
		 * no white spaces, and has minimum length of 8 and maximum length of 20.
		 */
		@NotBlank
		@Size(min = 8, max = 30, message = "Password must be between 8-30 characters")
		@Pattern(
				regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).*$",
				message = "Password must contain at least one number, one lower-case letter, " +
						"one upper-case letter, and no white spaces."
		)
		public final String password;

		/**
		 * Phone must be a sequence of 10 numbers.
		 */
		@NotBlank
		@Pattern(regexp = "^\\d{10}$", message = "Phone must have 10 digits")
		public final String phone;

		public RegisterForm(String name, String email, String password, String phone) {
			this.name = name;
			this.email = email;
			this.password = password;
			this.phone = phone;
		}

	}

	@Getter
	public static class LoginForm {

		@NotBlank
		@Email(message = "Not a valid email")
		public final String email;

		@NotBlank
		public final String password;

		public LoginForm(String email, String password) {
			this.email = email;
			this.password = password;
		}

	}
}
