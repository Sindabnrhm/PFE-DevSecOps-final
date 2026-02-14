package tn.arabsoft.auth.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.arabsoft.auth.entity.Sanction;
import tn.arabsoft.auth.entity.Service;
import tn.arabsoft.auth.entity.TypeConge;
import tn.arabsoft.auth.repository.ServiceRepo;
import tn.arabsoft.auth.repository.TypeCongeRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/service")
public class ServiceController {
	@Autowired
	ServiceRepo serviceRepository;
	@Autowired 
	TypeCongeRepository typeCongeRepository;
	
	@PostMapping("/addService")
	public Service addService(@RequestBody Service serv)
	{
		return serviceRepository.save(serv);
		
	}
	@PostMapping("/addTypeConge")
	public TypeConge addTypeConge(@RequestBody TypeConge type)
	{		
		return typeCongeRepository.save(type);	
	}
	@GetMapping("/getTypeConge")
	public List<TypeConge> getTypeConge()
	{
		return typeCongeRepository.findAll();
	}
	@GetMapping("/getService")
	public List<Service> getService()
	{
		return serviceRepository.findAll();
	}

	@DeleteMapping(value = "/service/{id}")
	public boolean deleteService(@PathVariable Long id) {
		
		serviceRepository.deleteById(id);
		return true;
	}
	@DeleteMapping(value = "/type/{id}")
	public boolean deleteType(@PathVariable Long id) {
		
		typeCongeRepository.deleteById(id);
		return true;
	}
}
