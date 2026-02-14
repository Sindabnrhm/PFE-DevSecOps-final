package tn.arabsoft.auth.controler;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.arabsoft.auth.entity.ChartConge;
import tn.arabsoft.auth.entity.Conge;
import tn.arabsoft.auth.entity.Sanction;
import tn.arabsoft.auth.entity.TypeConge;
import tn.arabsoft.auth.repository.ChartRepo;
import tn.arabsoft.auth.repository.CongeRepository;
import tn.arabsoft.auth.repository.SanctionRepository;
import tn.arabsoft.auth.repository.TypeCongeRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/conge")
public class CongeController {

	
	@Autowired
	CongeRepository congeRepository;
	@Autowired
	TypeCongeRepository typeconge;
	@Autowired
	SanctionRepository sanctionRepository;
	@Autowired
	ChartRepo chartRepo;

	@GetMapping("/get/{mat}")
	public List<Conge> getCongeByMat(@PathVariable("mat") String mat)
	{
		return congeRepository.getCongeByMat(mat);
	}
	@GetMapping("/getCng/{mat}")
	public List<Conge> getCongeByMonth(@PathVariable("mat") String mat)
	{
		return congeRepository.getCongeByM(mat);
	}
	@GetMapping("/getNbrCng/{mat}")
	public Conge getNbrConge(@PathVariable("mat") String mat)
	{
		return congeRepository.getNbrCng(mat);
	}

@PostMapping("/addSanction")
public Sanction addSanction(@RequestBody Sanction sanc)
{
	sanc.setNomSnaction("Vous avez depassez ton solde conges");
	
	return sanctionRepository.save(sanc);
	
}
	@PostMapping("/add")

	public void createChambre(@RequestBody Conge conge ) {
		 Long sld = this.congeRepository.getMaxSolde(conge.getMatriculeP());
		 long elapsedms =conge.getDateFin().getTime()-conge.getDateDebut().getTime();
		 long differenceDays = elapsedms / (24 * 60 * 60 * 1000);	
		 System.out.println(differenceDays);


		 if(sld!=null) {
//			 sld= sld.longValue();
			 System.out.println(sld);
			 conge.setSoldeCng(sld-differenceDays);
			 congeRepository.save(conge);
		 }
		 else {
			 System.out.println("else"+sld);

			 conge.setSoldeCng(-differenceDays);
			 congeRepository.save(conge); 
		 }
		
		
	}

	@GetMapping("/getTypeConge")
	public List<TypeConge> getTypeConge()
	{
		return typeconge.findAll();
	}
	
	@GetMapping("/getMaxSolde/{mat}")
	public Long getMaxSoldeCng(@PathVariable("mat") String mat)
	{
		return congeRepository.getMaxSolde(mat);
	}
	@GetMapping("/getDemandeChef/{serv}")
	public List<Conge> getDemandeChef(@PathVariable("serv") String serv)
	{
		return congeRepository.getDemandeChef(serv);
	}
	@GetMapping("/getDemandeChefNotNull/{serv}")
	public List<Conge> getDemandeChefNotNull(@PathVariable("serv") String serv)
	{
		return congeRepository.getDemandeChefRepNotNull(serv);
	}
	
	@GetMapping("/getDemandeRhNotNull")
	public List<Conge> getDemandeRhNotNull()
	{
		return congeRepository.getDemandeRhRepNotNull();
	}
	
	@GetMapping("/getDemandeRh")
	public List<Conge> getDemandeRh()
	{
		return congeRepository.getDemandeRh();
	}
	@GetMapping("/getRepChef/{mat}/{id}")
	public String getRepChef(@PathVariable("mat") String mat,@PathVariable("id") Long id)
	{
		return congeRepository.getRepChef(mat,id);
	}
	@DeleteMapping(value = "/{id}")
	public boolean deleteHotel(@PathVariable Long id) {
		
		congeRepository.deleteById(id);
		return true;
	}
	 @CrossOrigin
	  @PutMapping("/updateChef")
	  public ResponseEntity<Conge> updateUclt( @RequestBody Conge Ag) {
		  
		     Optional<Conge> AgData = congeRepository.findById(Ag.getIdConge());
		    if (AgData.isPresent()) {
		    	Conge agg = AgData.get();
		   agg.setRepChef(Ag.getRepChef());
		      
		     return new ResponseEntity<>(congeRepository.save(agg), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    } }
	 
	 @CrossOrigin
	  @PutMapping("/updateRh")
	  public ResponseEntity<Conge> updateRh( @RequestBody Conge Ag) {
		  
		     Optional<Conge> AgData = congeRepository.findById(Ag.getIdConge());
		    if (AgData.isPresent()) {
		    	Conge agg = AgData.get();
		   agg.setRepRh(Ag.getRepRh());
		      
		     return new ResponseEntity<>(congeRepository.save(agg), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    } }
}
