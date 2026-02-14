package tn.arabsoft.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

 
  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole nomRole;

  public Role() {

  }

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public ERole getNomRole() {
	return nomRole;
}

public void setNomRole(ERole nomRole) {
	this.nomRole = nomRole;
}

public Role(Integer id, ERole nomRole) {
	super();
	this.id = id;
	this.nomRole = nomRole;
}




}