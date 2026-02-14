package tn.arabsoft.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.arabsoft.auth.entity.user_role;

public interface UserRoleRepo extends JpaRepository<user_role, Long> {

	
	@Query(value="select * from user_roles where user_id=:userId",nativeQuery = true)
	public Optional<user_role> getByUser_id(@Param("userId")long userId);
}
