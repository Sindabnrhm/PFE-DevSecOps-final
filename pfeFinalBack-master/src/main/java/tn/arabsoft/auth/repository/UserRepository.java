package tn.arabsoft.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.arabsoft.auth.entity.Personnel;

public interface UserRepository extends JpaRepository<Personnel, Integer>{
	 Optional<Personnel> findByNom(String username);
	  Optional<Personnel> findByMatriculeP(String matricule);
	  Boolean existsByMatriculeP(String mat);
	  @Query(value="SELECT * FROM personnel where id_employe=:id\r\n",nativeQuery=true)
	  public Personnel getpers(@Param("id")Integer id);  
	  
//SELECT matriculep FROM personnel ORDER BY id_employe DESC LIMIT 1
@Query(value="SELECT matriculep FROM personnel ORDER BY id_employe DESC LIMIT 1\r\n",nativeQuery=true)
public String getMat();
}
