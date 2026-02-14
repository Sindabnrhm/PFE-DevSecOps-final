package tn.arabsoft.auth.repository;

import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.arabsoft.auth.entity.ChartConge;
import tn.arabsoft.auth.entity.Conge;

public interface CongeRepository extends JpaRepository<Conge, Long> {

	@Query(value="select * from conge where matriculep=:mat",nativeQuery=true)
	public List<Conge> getCongeByMat(@Param("mat") String mat);
	
	
	@Query(value="select id_conge,matriculeP,date_cng,date_debut,date_fin,duree,"
			+ "statut,solde_cng,rep_chef,rep_rh,  COUNT(id_conge) nom ,id_employe,id_type,DATE_FORMAT(date_cng,'%m/%Y') prenom from "
			+ "conge  where matriculep=:mat GROUP by DATE_FORMAT(date_cng,'%m-%Y')",nativeQuery=true)
	public List<Conge> getCongeByM(@Param("mat") String mat);
	@Query(value="SELECT solde_cng FROM `conge` WHERE matriculep=:mat ORDER BY date_Cng DESC LIMIT 1\r\n",nativeQuery=true)
	public Long getMaxSolde(@Param("mat") String mat);
	
	@Query(value="SELECT rep_chef FROM `conge` WHERE matriculep=:mat and id_conge=:id\r\n",nativeQuery=true)
	public String getRepChef(@Param("mat") String mat,@Param("id")Long id);
	@Query(value="SELECT c.*,(p.nom)name,(p.prenom)lname FROM `conge` c ,personnel p WHERE c.matriculeP=p.matriculeP and "
			+ "c.matriculeP in (select matriculeP from personnel where serv=:serv)and c.rep_chef IS NULL\r\n",nativeQuery=true)
	public List<Conge> getDemandeChef(@Param("serv") String serv);
	
	@Query(value="SELECT c.*,p.nom,p.prenom FROM `conge` c ,personnel p WHERE c.matriculeP=p.matriculeP and c.matriculeP in (select matriculeP from personnel where serv=:serv) and c.rep_chef in ('O','N')",nativeQuery=true)
	public List<Conge> getDemandeChefRepNotNull(@Param("serv") String serv);
	
	@Query(value="SELECT c.*,p.nom,p.prenom FROM `conge` c ,personnel p WHERE c.matriculeP=p.matriculeP "
			+ " and c.rep_rh in('O','N')",nativeQuery=true)
	public List<Conge> getDemandeRhRepNotNull();
	
	@Query(value="SELECT c.*,p.nom,p.prenom FROM `conge` c ,personnel p WHERE c.matriculeP=p.matriculeP and "
			+ " c.rep_chef='O' "
			+ " and c.rep_rh is null\r\n",nativeQuery=true)
	public List<Conge> getDemandeRh();
	
	@Query(value="select COUNT(idConge) nom ,DATE_FORMAT(date_cng,'%m/%Y') prenom "
			+ "from conge where matriculep=:mat GROUP by DATE_FORMAT(date_cng,'%m-%Y')\r\n",nativeQuery=true)
	public List<Conge> getCngByMonth(@Param("mat") String mat);
	
	
	@Query(value="select id_conge,matriculeP,date_cng,date_debut,date_fin,duree,"
			+ "statut,solde_cng,rep_chef,rep_rh,  COUNT(id_conge) nom ,id_employe,id_type,DATE_FORMAT(date_cng,'%m/%Y') prenom from "
			+ "conge  where matriculep=:mat",nativeQuery=true)
	
	public Conge getNbrCng(@Param("mat") String mat);

//@Query("select NEW com.mypackage.CustomerAmountResult(
   // o.customer.surname, sum(o.amount)) 
//from Order as o
//group by o.customer.surname") 
//	SELECT c.* FROM `conge` c ,personnel p WHERE c.matriculeP=p.matriculeP and c.matriculeP in (select matriculeP from personnel where serv=1)
//SELECT c.* FROM conge c,service s,personnel p WHERE s.id_service=p.serv and c.matriculeP=p.matriculeP and p.serv=:serv
}
