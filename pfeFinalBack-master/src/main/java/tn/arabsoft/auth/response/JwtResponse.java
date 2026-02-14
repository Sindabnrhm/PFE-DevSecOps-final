package tn.arabsoft.auth.response;

import java.util.List;

import tn.arabsoft.auth.entity.Service;

public class JwtResponse {
	 private String token;
	  private String type = "Bearer";
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	private String email ;
	private String matriculeP ;
	private String nom ;
	private String prenom ;
	private String departement ;
	private String nomResponsable ;
	private int numTel ;
	private String poste ;
	private String password ;
	private Service serv;
	private List<String> roles;
	public JwtResponse() {
		super();
	}
	
	

	public JwtResponse(String token, String type, String email, String matriculeP, String nom, String prenom,
			String departement, String nomResponsable, int numTel, String poste, String password, Service serv,
			List<String> roles) {
		super();
		this.token = token;
		this.type = type;
		this.email = email;
		this.matriculeP = matriculeP;
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.nomResponsable = nomResponsable;
		this.numTel = numTel;
		this.poste = poste;
		this.password = password;
		this.serv = serv;
		this.roles = roles;
	}
	public JwtResponse(String token, String type, String email, String matriculeP, String nom, String prenom,
			String departement, String nomResponsable, int numTel, String poste, String password, List<String> roles) {
		super();
		this.token = token;
		this.type = type;
		this.email = email;
		this.matriculeP = matriculeP;
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.nomResponsable = nomResponsable;
		this.numTel = numTel;
		this.poste = poste;
		this.password = password;
		this.roles = roles;
	}
	
	
	public JwtResponse(String token, String type, Service serv, String email, String matriculeP, String nom,
			String prenom, String departement, String nomResponsable, int numTel, String poste, String password,
			List<String> roles) {
		super();
		this.token = token;
		this.type = type;
		this.serv = serv;
		this.email = email;
		this.matriculeP = matriculeP;
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.nomResponsable = nomResponsable;
		this.numTel = numTel;
		this.poste = poste;
		this.password = password;
		this.roles = roles;
	}
	public String getMatriculeP() {
		return matriculeP;
	}
	public void setMatriculeP(String matriculeP) {
		this.matriculeP = matriculeP;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getNomResponsable() {
		return nomResponsable;
	}
	public void setNomResponsable(String nomResponsable) {
		this.nomResponsable = nomResponsable;
	}
	public int getNumTel() {
		return numTel;
	}
	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Service getServ() {
		return serv;
	}
	public void setServ(Service serv) {
		this.serv = serv;
	}


}
