package by.company.library.bean;

public class User {
	private UserPermission permission;
	private String login;
	private String hashPassword;

	public User(String login, String password, UserPermission permission) {
		setLogin(login);
		setPassword(password);
		setPermission(permission);
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String GetLogin() {
		return login;
	}

	public void setPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public String GetPassword() {
		return hashPassword;
	}

	public void setPermission(UserPermission permission) {
		this.permission = permission;
	}

	public UserPermission GetPermission() {
		return permission;
	}
}
