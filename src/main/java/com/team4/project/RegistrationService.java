package com.team4.project;

import java.util.Optional;

public interface RegistrationService {

	Iterable<Registration> getRegistrations();

	Optional<Registration> getRegistrationById(long id);
	
	Registration addRegistration(Registration r);
	
	void updateRegistration(Registration r);

	void deleteRegistration(Registration r);

	void deleteRegistrationById(long id);

}
