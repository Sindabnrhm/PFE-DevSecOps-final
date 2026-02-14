package tn.arabsoft.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.arabsoft.auth.entity.ChartConge;

public interface ChartRepo extends JpaRepository<ChartConge, Long> {
	@Query(value="select COUNT(id_conge) nbrCng ,DATE_FORMAT(date_cng,'%m/%Y') dateCng "
			+ "from conge where matriculep=:mat GROUP by DATE_FORMAT(date_cng,'%m-%Y')\r\n",nativeQuery=true)
	public List<ChartConge> getCngByMonth(@Param("mat") String mat);
}
