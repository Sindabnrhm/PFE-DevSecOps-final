package tn.arabsoft.auth.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class ChartConge implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id_conge;
	private String date_cng;
	
	public ChartConge(Long id_conge, String date_cng) {
		super();
		this.id_conge = id_conge;
		this.date_cng = date_cng;
	}

	public Long getId_conge() {
		return id_conge;
	}

	public void setId_conge(Long id_conge) {
		this.id_conge = id_conge;
	}

	public String getDate_cng() {
		return date_cng;
	}

	public void setDate_cng(String date_cng) {
		this.date_cng = date_cng;
	}

	public ChartConge() {
		// TODO Auto-generated constructor stub
	}
	

}
