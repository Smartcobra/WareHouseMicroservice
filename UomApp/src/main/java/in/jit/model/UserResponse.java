package in.jit.model;

public class UserResponse {

	private String token;
	private String note;

	public UserResponse() {
	}

	public UserResponse(String token, String note) {
		this.token = token;
		this.note = note;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
