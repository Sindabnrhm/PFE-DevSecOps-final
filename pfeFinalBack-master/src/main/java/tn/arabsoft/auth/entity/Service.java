package tn.arabsoft.auth.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="Service")

	
	public class Service implements Serializable {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	      private Long idService ;
	      private String cod_serv ;
	      private String lib_serv;
	      private String matriculeChef;


	     public Service() {
			// TODO Auto-generated constructor stub
		}

		public Long getIdService() {
			return idService;
		}

		public void setIdService(Long idService) {
			this.idService = idService;
		}

		public String getCod_serv() {
			return cod_serv;
		}

		public void setCod_serv(String cod_serv) {
			this.cod_serv = cod_serv;
		}

		public String getLib_serv() {
			return lib_serv;
		}

		public void setLib_serv(String lib_serv) {
			this.lib_serv = lib_serv;
		}

		public String getMatriculeChef() {
			return matriculeChef;
		}

		public void setMatriculeChef(String matriculeChef) {
			this.matriculeChef = matriculeChef;
		}

		public Service(Long idService, String cod_serv, String lib_serv, String matriculeChef) {
			super();
			this.idService = idService;
			this.cod_serv = cod_serv;
			this.lib_serv = lib_serv;
			this.matriculeChef = matriculeChef;
		}

		
	}
