package tn.arabsoft.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.arabsoft.auth.entity.ERole;
import tn.arabsoft.auth.entity.Role;

public interface RoleRepository extends JpaRepository <Role, Integer>{
	 Optional<Role> findByNomRole(ERole name);

}
