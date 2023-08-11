package com.team4.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MockRegistrationService implements RegistrationService {
	
	static ArrayList<Registration> registrations = new ArrayList<Registration>();
	
	static int idx;

	static {  
		Registration r1 = new Registration("2", "2", new Date(), "these are notes");
		r1.setId(0);
		
		Registration r2 = new Registration("0", "1", new Date(), "these are notes");
		r2.setId(1);
		
		Registration r3 = new Registration("1", "0", new Date(), "these are notes");
		r3.setId(2);
		
		registrations.add(r1);
		registrations.add(r2);
		registrations.add(r3);
		
		idx = 3;
	}

	@Override
	public Iterable<Registration> getRegistrations() {
		return registrations;
	}

	@Override
	public Optional<Registration> getRegistrationById(long id) {
		for (Registration registration : registrations) {
			if (registration.getId() == id) {
				return Optional.of(registration);
			}
		}
		return Optional.empty();
	}

	@Override
	public void deleteRegistrationById(long id) {
		for (Registration registration : registrations) {
			if (registration.getId() == id) {
				registrations.remove(registration);
			}
		}
		
	}

	@Override
	public Registration addRegistration(Registration r) {
		r.setId((long)(idx));
		idx++;
		registrations.add(r);
		return r;
		
	}

	@Override
	public void updateRegistration(Registration r) {
		for (int i = 0; i < registrations.size(); i++) {
			Registration registration = registrations.get(i);
			if (registration.getId() == r.getId()) {
				registrations.set(i, r);
			}
		}
		
	}

	@Override
	public void deleteRegistration(Registration r) {
		registrations.remove(r);
		
	}

}
