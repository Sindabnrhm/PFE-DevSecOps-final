package tn.arabsoft.auth.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class TypeConge implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idType;
	private String nomTypeconge;
	
	
	
//
//@OneToMany(mappedBy = "typeConge")
//@JsonProperty(access = Access.WRITE_ONLY)
//
//private List<Conge> Conges;
	
	public TypeConge() {
		// TODO Auto-generated constructor stub
	}

	public TypeConge(String nomTypeconge) {
		super();
		this.nomTypeconge = nomTypeconge;
	}

	public Long getIdType() {
		return idType;
	}

	public void setIdType(Long idType) {
		this.idType = idType;
	}

	public String getNomTypeconge() {
		return nomTypeconge;
	}

	public void setNomTypeconge(String nomTypeconge) {
		this.nomTypeconge = nomTypeconge;
	}





	

}