package by.company.library.bean;

public enum UserPermission {
	USER("user"), ADMIN("admin"), UNSUPPORTED("unsuppported");

	private final String text;

	private UserPermission(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

	public static UserPermission getPermission(String value) {
		UserPermission userPermission = null;
		for (UserPermission permission : UserPermission.values()) {
			if (value.compareTo(permission.toString()) == 0) {
				userPermission = permission;
				break;
			}
		}
		return userPermission == null ? UserPermission.UNSUPPORTED : userPermission;
	}
}
