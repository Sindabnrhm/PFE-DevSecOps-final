package tn.arabsoft.auth.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Personnel")
public class Personnel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idEmploye;
	private String email ;
	private String matriculeP ;
	private String nom ;
	private String prenom ;
	private String departement ;
	private String nomResponsable ;
	private int numTel ;
	private String poste ;
	private String password ;
@ManyToOne
@JoinColumn(name="serv")
private Service serv;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	public Personnel() {
		super();
	}
	public Integer getIdEmploye() {
		return idEmploye;
	}
	public void setIdEmploye(Integer idEmploye) {
		this.idEmploye = idEmploye;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMatriculeP() {
		return matriculeP;
	}
	public void setMatriculeP(String matriculeP) {
		this.matriculeP = matriculeP;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public Personnel(String email, String matriculeP, String nom, String prenom, String departement,
			String nomResponsable, int numTel, String poste, String password) {
		super();
		this.email = email;
		this.matriculeP = matriculeP;
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.nomResponsable = nomResponsable;
		this.numTel = numTel;
		this.poste = poste;
		this.password = password;
	}
	
	public Personnel(String email, String matriculeP, String nom, String prenom, String departement,
			String nomResponsable, int numTel, String poste, String password, Service serv) {
		super();
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
	}



	public Service getServ() {
		return serv;
	}
	public void setServ(Service serv) {
		this.serv = serv;
	}
	
	

	}