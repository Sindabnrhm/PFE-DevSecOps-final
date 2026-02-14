package tn.arabsoft.auth.controler;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.arabsoft.auth.entity.Role;
import tn.arabsoft.auth.entity.Service;
import tn.arabsoft.auth.entity.user_role;
import tn.arabsoft.auth.entity.Personnel;
import tn.arabsoft.auth.payload.request.LoginRequest;
import tn.arabsoft.auth.payload.request.SignupRequest;
import tn.arabsoft.auth.repository.RoleRepository;
import tn.arabsoft.auth.repository.ServiceRepo;
import tn.arabsoft.auth.repository.UserRepository;
import tn.arabsoft.auth.repository.UserRoleRepo;
import tn.arabsoft.auth.response.JwtResponse;
import tn.arabsoft.auth.response.MessageResponse;
import tn.arabsoft.auth.security.jwt.JwtUtils;
import tn.arabsoft.auth.security.service.UserDetailsImpl;
import tn.arabsoft.auth.security.service.UserDetailsServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class Auth {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserDetailsServiceImpl  serv;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;
  @Autowired
  UserRoleRepo userRole;
  @Autowired
  JwtUtils jwtUtils;
  @Autowired
  ServiceRepo serviceRepo;
  @GetMapping("/get/{mat}")
  public UserDetails get(@PathVariable String mat){
  	return this.serv.loadUserByUsername(mat);
  }
  
  @GetMapping("/gett/{mat}")
  public Optional<Personnel> gett(@PathVariable String mat){
  	return this.userRepository.findByMatriculeP(mat);
  }
  
  @GetMapping("/getUsers")
  public List<Personnel> getUsers(){
  	return this.userRepository.findAll();
  }
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getMatriculeP(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
    Optional<Personnel> pers = userRepository.findByMatriculeP(loginRequest.getMatriculeP());
    System.out.println(pers);
    return ResponseEntity.ok(new JwtResponse(jwt, 
                         "Bearer", 
                         userDetails.getNomResponsable(), 
                         userDetails.getMatriculeP(),
                         userDetails.getEmail(), 
                          userDetails.getPassword(),userDetails.getNom(),userDetails.getPrenom(),userDetails.getNumTel()
                          ,userDetails.getPoste(),userDetails.getDepartement(),userDetails.getServ(),roles));
  }
 
 @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		  if (userRepository.existsByMatriculeP(signUpRequest.getMatriculeP())) { return
		 ResponseEntity .badRequest() .body(new
		 MessageResponse("Error: Username is already taken!")); }
		 

	/*
	 * if (userRepository.existsByEmail(signUpRequest.getEmail())) { return
	 * ResponseEntity .badRequest() .body(new
	 * MessageResponse("Error: Email is already in use!")); }
	 */
    //Create new user's account
    Personnel user = new Personnel( signUpRequest.getEmail(), signUpRequest.getMatriculeP(),
    		signUpRequest.getNom(), signUpRequest.getPrenom(), signUpRequest.getDepartement(), 
    		signUpRequest.getNomResponsable(),
    		signUpRequest.getNumTel(), signUpRequest.getPoste(), 
    		encoder.encode(signUpRequest.getPassword()),signUpRequest.getServ());

    Integer intRoles = signUpRequest.getRoles();
	 Set<Role> roles = new HashSet<>();
    if(intRoles!=null) {
        System.out.println(intRoles);
        Role userRole = roleRepository.findById(intRoles)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
           roles.add(userRole);
    }else {
    
         Role userRole = roleRepository.findById(1)
                 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
    }

    
    String matMax=this.userRepository.getMat();
    long number = Long.parseLong(matMax);
    number += 1;
    String output = String.format("%04d", number + 1);
    System.out.println(output);
    user.setMatriculeP(output);
    user.setRoles(roles);

    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    

  }
 
 @GetMapping("/get")
 public List<Personnel> getPersonnel(){
	 return userRepository.findAll();
 }
 @GetMapping("/getService")
 public List<Service> getService(){
	 return serviceRepo.findAll();
 }

 @GetMapping("/getuseRole/{use}")
 public Optional<user_role> getUse(@PathVariable long use){
	 return userRole.getByUser_id(use);
 }
 @PutMapping("/updatePass")
 
 public ResponseEntity<Personnel> updatePass( @RequestBody Personnel Ag) {
 
    Optional<Personnel> AgData = userRepository.findById(Ag.getIdEmploye());
   if (AgData.isPresent()) {
	   Personnel agg = AgData.get();
  agg.setPassword(encoder.encode(Ag.getPassword()));

    return new ResponseEntity<>(userRepository.save(agg), HttpStatus.OK);
   } else {
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   } }
 
 @PutMapping("/setRole")
 
 public ResponseEntity<user_role> updateRh( @RequestBody user_role Ag) {

	    Optional<user_role> AgData = userRole.getByUser_id(Ag.getUser_id());
		   System.out.println(AgData);

	   if (AgData.isPresent()) {
		   user_role agg = AgData.get();
	  agg.setRole_id(Ag.getRole_id());

	    return new ResponseEntity<>(userRole.save(agg), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   } }
	 
 @PostMapping("/add")
 public Personnel addPers(@RequestBody Personnel pers) {
	return userRepository.save(pers);
	 
 }
 }

