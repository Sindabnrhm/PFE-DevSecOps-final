package tn.arabsoft.auth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_roles")
@Entity
public class user_role {
	
	@Id
	private long user_id ;
	private long role_id  ;
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getRole_id() {
		return role_id;
	}
	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}
	public user_role(long user_id, long role_id) {
		super();
		this.user_id = user_id;
		this.role_id = role_id;
	}
	public user_role() {
		super();
		// TODO Auto-generated constructor stub
	}



}
