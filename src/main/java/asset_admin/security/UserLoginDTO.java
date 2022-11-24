package asset_admin.security;

import javax.validation.constraints.NotEmpty;

public class UserLoginDTO {
	
	@NotEmpty(message = "Username is required")
	private String username;
	@NotEmpty(message = "Password is required")
	private String password;
	
	public UserLoginDTO(){
		super();
	}
	
	public UserLoginDTO(@NotEmpty(message = "Username is required") String username,
			@NotEmpty(message = "Password is required") String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
