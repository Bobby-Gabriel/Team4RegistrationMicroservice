package com.team4.project;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RequestMapping("/gateway/registrations")
@RestController
@CrossOrigin
public class RegistrationGateway {

	static final String JSON = "application/json";


	@Autowired
	RegistrationValet registrationService;

	
	@GetMapping
	public Iterable<Registration> getAllRegistrations(HttpServletResponse response) {
		
		response.setStatus(HttpServletResponse.SC_OK);

		return registrationService.getRegistrations();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Registration> getOneSingleRegistration(@PathVariable long id, HttpServletResponse response) {
		
		response.setStatus(HttpServletResponse.SC_OK);

		return registrationService.getRegistrationById(id);
	}
	
	
	@PostMapping(consumes = JSON, produces = JSON)
	@ResponseBody
	public ResponseEntity<?> createRegistration(@RequestBody Registration register, HttpServletResponse response){
	
		Registration postedRegistration = registrationService.addRegistration(register);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(register.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		
		return new ResponseEntity<>(postedRegistration, headers, HttpStatus.OK);
	}
	
	
	@PutMapping("/{registrationId}")
	public ResponseEntity<?> putRegistration(@RequestBody Registration newR, @PathVariable long registrationId) {
		
		if (newR.getId() != registrationId) {
			return ResponseEntity.badRequest().build();
		}

		registrationService.updateRegistration(newR);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRegistrationById(@PathVariable long id){
	    
		registrationService.deleteRegistrationById(id);

		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteRegistration(Registration r){
	    
		registrationService.deleteRegistration(r);

		return ResponseEntity.ok().build();
	}
}
