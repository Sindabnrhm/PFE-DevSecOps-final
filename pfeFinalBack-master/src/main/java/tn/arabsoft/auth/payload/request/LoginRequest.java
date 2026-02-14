package tn.arabsoft.auth.payload.request;

public class LoginRequest {
	private String matriculeP;
	private String password;
	public String getMatriculeP() {
		return matriculeP;
	}
	public void setMatriculeP(String matriculeP) {
		this.matriculeP = matriculeP;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginRequest(String matriculeP, String password) {
		super();
		this.matriculeP = matriculeP;
		this.password = password;
	}
	public LoginRequest() {
		super();
	}
	
	
}
