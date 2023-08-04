package com.team4.project;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationMongoService implements RegistrationService {

	@Autowired
	RegistrationMongoRepository repo;
	
	@Override
	public Iterable<Registration> getRegistrations() {
		return repo.findAll();
	}

	@Override
	public Optional<Registration> getRegistrationById(String id) {
		return repo.findById(id);
	}

	@Override
	public void deleteRegistrationById(String id) {
		repo.deleteById(id);
	}
	
	@Override
	public void deleteRegistration(Registration r) {
		repo.delete(r);
	}

	@Override
	public Registration addRegistration(Registration r) {
		return repo.save(r);
	}

	@Override
	public Registration updateRegistration(Registration r) {
		return repo.save(r);
		
	}
}
